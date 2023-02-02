package com.mynewsapp.mentor.ui.countries

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.R
import com.mynewsapp.mentor.data.model.countries.Country
import com.mynewsapp.mentor.databinding.ActivityCountryBinding
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.base.BaseActivity
import com.mynewsapp.mentor.utils.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryActivity : BaseActivity<ActivityCountryBinding, CountryViewModel>
    (ActivityCountryBinding::inflate) {

    @Inject
    lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()

        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setupObserver()

    }

    private fun setupObserver() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.countryList.collect{ it ->

                    when(it.status){

                        Status.SUCCESS->{
                            binding.progressBar.visibility=View.GONE
                            binding.recyclerView.visibility=View.VISIBLE

                            it.data?.let {
                                renderList(it)
                            }

                        }
                        Status.ERROR->{
                            binding.progressBar.visibility= View.VISIBLE
                            Toast.makeText(this@CountryActivity, it.message, Toast.LENGTH_LONG).show()

                        }
                        Status.LOADING->{
                            binding.progressBar.visibility= View.VISIBLE
                            binding.recyclerView.visibility= View.GONE
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

    private fun setupUi() {

        binding.recyclerView.adapter=adapter

    }

    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }

}