package com.mynewsapp.mentor.ui.languages

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.databinding.ActivityLanguageNewsBinding
import com.mynewsapp.mentor.di.component.ActivityComponent
import com.mynewsapp.mentor.ui.base.BaseActivity
import com.mynewsapp.mentor.ui.base.UiState
import com.mynewsapp.mentor.utils.AppConstant
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageNewsActivity : BaseActivity<ActivityLanguageNewsBinding, LanguageNewsViewModel>() {

    @Inject
    lateinit var adapter: LanguageNewsAdapter

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getViewBinding(): ActivityLanguageNewsBinding {
        return ActivityLanguageNewsBinding.inflate(layoutInflater)
    }

    override fun setUpUi() {
        binding.recyclerView.adapter = adapter

        intent.getStringExtra(AppConstant.LANGUAGE)?.let {
            viewModel.fetchNewsWithLanguage(it)
        }
        intent.getStringExtra(AppConstant.COUNTRY)?.let {
            viewModel.fetchNewsWithCountry(it)
        }
    }

    override fun setUpObserver() {
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.articleList.collect { it ->

                    when (it) {

                        is UiState.Success -> {

                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            it.data?.let {
                                renderList(it)
                            }
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
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


}