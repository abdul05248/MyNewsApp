package com.mynewsapp.mentor.ui.topHeadlines

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import com.mynewsapp.mentor.databinding.ActivityTopHeadlinesBinding
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.base.BaseActivity
import com.mynewsapp.mentor.utils.Status
import com.mynewsapp.mentor.utils.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

///Ask$   ActivityTopHeadlinesBinding::inflate  ??syntex
class TopHeadlinesActivity :
    BaseActivity<ActivityTopHeadlinesBinding, TopHeadlinesViewModel>
        (ActivityTopHeadlinesBinding::inflate) {

    @Inject
    lateinit var adapter: TopHeadlinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)

        setupUI()
        setupObserver()

    }

    private fun setupUI() {

        binding.recyclerView.adapter = adapter

        adapter.itemClickListener = {
            Utils.openCustomTabUrl(this, it.url)
        }

        adapter.javaItemClickListener = object : JavaItemClickListener {
            override fun onClick(top: TopHeadlines) {
                Utils.openCustomTabUrl(this@TopHeadlinesActivity, top.url)
            }
        }
    }

    private fun setupObserver() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.articleList.collect { it ->

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