package com.mynewsapp.mentor.ui.topHeadlines

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.data.local.entities.TopHeadlines
import com.mynewsapp.mentor.databinding.ActivityTopHeadlinesBinding
import com.mynewsapp.mentor.di.component.ActivityComponent
import com.mynewsapp.mentor.ui.base.BaseActivity
import com.mynewsapp.mentor.ui.base.UiState
import com.mynewsapp.mentor.utils.Utils
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopHeadlinesActivity :
    BaseActivity<ActivityTopHeadlinesBinding, TopHeadlinesViewModel>() {

    @Inject
    lateinit var adapter: TopHeadlinesAdapter


    private fun renderList(articleList: List<TopHeadlines>) {

        adapter.addData(articleList)
        adapter.notifyDataSetChanged()

    }

    override fun getViewBinding(): ActivityTopHeadlinesBinding {
        return ActivityTopHeadlinesBinding.inflate(layoutInflater)
    }

    override fun setUpUi() {

        binding.recyclerView.adapter = adapter

        adapter.itemClickListener = {
            it.url?.let { it1 -> Utils.openCustomTabUrl(this, it1) }
        }

        adapter.javaItemClickListener = object : JavaItemClickListener {
            override fun onClick(top: TopHeadlines) {
                top.url?.let { Utils.openCustomTabUrl(this@TopHeadlinesActivity, it) }
            }
        }
    }

    override fun setUpObserver() {


        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.articleList.collect {

                    when (it) {

                        is UiState.Success -> {

                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            renderList(it.data)
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE

                            Toast.makeText(this@TopHeadlinesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }


                    }

                }

            }
        }

    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

}