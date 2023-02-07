package com.mynewsapp.mentor.ui.countries

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.data.model.countries.Country
import com.mynewsapp.mentor.databinding.ActivityCountryBinding
import com.mynewsapp.mentor.di.component.ActivityComponent
import com.mynewsapp.mentor.ui.base.BaseActivity
import com.mynewsapp.mentor.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryActivity : BaseActivity<ActivityCountryBinding, CountryViewModel>() {

    @Inject
    lateinit var adapter: CountryAdapter

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun getViewBinding(): ActivityCountryBinding {

        return ActivityCountryBinding.inflate(layoutInflater)
    }

    override fun setUpUi() {
        binding.recyclerView.adapter = adapter

    }

    override fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.countryList.collect { it ->

                    when (it) {

                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerView.visibility = View.VISIBLE

                            renderList(it.data)

                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.VISIBLE
                            Toast.makeText(this@CountryActivity, it.message, Toast.LENGTH_LONG)
                                .show()

                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                    }

                }

            }
        }

    }

    private fun renderList(it: List<Country>) {
        adapter.addData(it)
        adapter.notifyDataSetChanged()

    }

}