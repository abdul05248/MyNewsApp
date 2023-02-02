package com.mynewsapp.mentor.ui.languages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.R
import com.mynewsapp.mentor.data.model.languages.Language
import com.mynewsapp.mentor.databinding.ActivityLanguageBinding
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.ui.base.BaseActivity
import com.mynewsapp.mentor.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageActivity : BaseActivity<ActivityLanguageBinding, LanguageViewModel>
    (ActivityLanguageBinding::inflate) {

    @Inject
    lateinit var adapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setupObserver()

    }

    private fun setupObserver() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.languageList.collect{

                    when(it.status){

                        Status.LOADING->{
                            binding.progressBar.visibility= View.VISIBLE
                            binding.recyclerView.visibility= View.GONE
                        }

                        Status.SUCCESS->{
                            binding.progressBar.visibility= View.GONE
                            binding.recyclerView.visibility= View.VISIBLE

                            it.data?.let {
                                renderList(it)
                            }
                        }

                        Status.ERROR->{
                            binding.recyclerView.visibility= View.GONE
                            Toast.makeText(this@LanguageActivity, it.message, Toast.LENGTH_LONG).show()
                        }

                    }

                }

            }

        }

    }

    private fun renderList(it: List<Language>) {

        adapter.addData(it)
        adapter.notifyDataSetChanged()

    }

    private fun setupUi() {

        binding.recyclerView.adapter = adapter

    }

    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)

    }
}