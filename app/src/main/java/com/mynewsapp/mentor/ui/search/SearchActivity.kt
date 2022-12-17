package com.mynewsapp.mentor.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesAdapter
import com.mynewsapp.mentor.utils.Resource
import com.mynewsapp.mentor.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySearchBinding

    @Inject
    lateinit var adapter: TopHeadlinesAdapter

    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()

    }

    private fun setupObserver() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                searchViewModel.searchList.collect{

                    when (it.status) {

                        Status.SUCCESS -> {

                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            it.data?.let {
                                renderList(it)
                            }
                        }

                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE

                            Toast.makeText(this@SearchActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }


                    }

                }

            }
        }

    }

    private fun renderList(it: List<Article>) {

        adapter.addData(it)
        adapter.notifyDataSetChanged()

    }

    private fun setupUI() {

        binding.recyclerView.adapter = adapter

        binding.  searchView.setOnQueryTextListener(object :OnQueryTextListener,

            SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {



                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

        })


    }

    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }
}