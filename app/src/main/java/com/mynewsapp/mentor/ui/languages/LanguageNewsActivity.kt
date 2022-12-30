package com.mynewsapp.mentor.ui.languages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.databinding.ActivityLanguageNewsBinding
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesAdapter
import com.mynewsapp.mentor.utils.AppConstant
import com.mynewsapp.mentor.utils.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageNewsActivity : AppCompatActivity() {

    lateinit var binding:ActivityLanguageNewsBinding

    @Inject
    lateinit var adapter: LanguageNewsAdapter

    @Inject
    lateinit var languageNewsViewModel: LanguageNewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupObserver()

    }

    private fun setupUI() {

        binding.recyclerView.adapter = adapter

        intent.getStringExtra(AppConstant.LANGUAGE)?.let {
            languageNewsViewModel.fetchNewsWithLanguage(it)
        }
        intent.getStringExtra(AppConstant.COUNTRY)?.let {
            languageNewsViewModel.fetchNewsWithCountry(it)
        }
    }

    private fun setupObserver() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                languageNewsViewModel.articleList.collect { it ->

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

                            Toast.makeText(this@LanguageNewsActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }


                    }

                }

            }
        }

    }

    private fun renderList(articleList: List<Article>) {

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