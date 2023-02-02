package com.mynewsapp.mentor.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView.GONE
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.databinding.ActivitySearchBinding
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>
    (ActivitySearchBinding::inflate) {

    @Inject
    lateinit var adapter: SearchPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun setupUI() {

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch() {
            binding.searchView.getQueryTextChangeStateFlow()
                .debounce(200)
                .filter {

                    val validQuery = it.isNotEmpty()

                    if (!validQuery) {
                        binding.progressBar.visibility = View.GONE
                        adapter.submitData(lifecycle, PagingData.empty())
                        binding.recyclerView.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.VISIBLE

                    }

                    return@filter validQuery

                }
                .flowOn(Dispatchers.Main)
                .distinctUntilChanged()
                .flatMapLatest {
                    return@flatMapLatest viewModel.fetchResult(it)
                        .catch { e ->
                            Log.d("apiError", e.message.toString())
//                            emitAll(flowOf(emptyList()))//Ask$ why emitAll not working?
                            adapter.submitData(lifecycle, PagingData.empty())
                            //Should I pass empty list here?
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.d("testPaging", it.toString())

                    binding.progressBar.visibility = GONE
                    binding.recyclerView.visibility = View.VISIBLE
//                    renderList(it)
                    adapter.submitData(lifecycle, it)

                }
        }
    }


    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }

    private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

        val query = MutableStateFlow("")

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                query.value = newText
                return true
            }
        })

        return query

    }
}