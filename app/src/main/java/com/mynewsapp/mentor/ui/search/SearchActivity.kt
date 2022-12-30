package com.mynewsapp.mentor.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView.GONE
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.R
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.databinding.ActivitySearchBinding
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.languages.LanguageNewsAdapter
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesAdapter
import com.mynewsapp.mentor.utils.Resource
import com.mynewsapp.mentor.utils.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    @Inject
    lateinit var adapter: LanguageNewsAdapter

    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

    }

    private fun renderList(it: List<Article>) {

        adapter.addData(it)
        adapter.notifyDataSetChanged()

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
                        renderList(emptyList())
                        binding.recyclerView.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.VISIBLE

                    }

                    return@filter validQuery

                }
                .flowOn(Dispatchers.Main)
                .distinctUntilChanged()
                .flatMapLatest {
                    return@flatMapLatest searchViewModel.fetchResult(it)
                        .catch { e ->
                            Log.d("apiError", e.message.toString())
                            emitAll(flowOf(emptyList()))
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {

                    binding.progressBar.visibility = GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    renderList(it)

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