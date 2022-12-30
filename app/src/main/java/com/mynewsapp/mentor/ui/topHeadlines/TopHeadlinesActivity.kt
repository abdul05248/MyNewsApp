package com.mynewsapp.mentor.ui.topHeadlines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.databinding.ActivityTopHeadlinesBinding
import com.mynewsapp.mentor.di.component.ActivityComponent
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.MainActivity
import com.mynewsapp.mentor.utils.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopHeadlinesBinding

    @Inject
    lateinit var topHeadlinesViewModel: TopHeadlinesViewModel

    @Inject
    lateinit var adapter: TopHeadlinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()

    }

    private fun setupUI() {

        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                topHeadlinesViewModel.articleList.collect { it ->

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

                            Toast.makeText(this@TopHeadlinesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }


                    }

                }

            }
        }

    }

    private fun renderList(articleList: List<TopHeadlines>) {

        adapter.addData(articleList)
        adapter.notifyDataSetChanged()

    }


    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }

}