package com.mynewsapp.mentor.ui.sources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.data.model.sources.Source
import com.mynewsapp.mentor.databinding.ActivityNewsSourceBinding
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import com.mynewsapp.mentor.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewsSourceBinding

    @Inject
    lateinit var adapter: NewsSourceAdapter

    @Inject
    lateinit var sourceViewModel: NewsSourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityNewsSourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        settUpUi()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                sourceViewModel.sourceList.collect{

                    when(it.status){

                        Status.SUCCESS->{

                            binding.progressBar.visibility= View.GONE
                            binding.recyclerView.visibility= View.VISIBLE

                            it.data?.let {
                                renderList(it)
                            }
                        }

                        Status.LOADING->{
                            binding.progressBar.visibility= View.VISIBLE
                            binding.recyclerView.visibility= View.GONE
                        }
                        Status.ERROR->{
                            binding.progressBar.visibility= View.GONE

                            Toast.makeText(this@NewsSourceActivity, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
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

    private fun settUpUi() {
        binding.recyclerView.adapter = adapter
    }

    private fun renderList(sourceList: List<Source>){

        adapter.addData(sourceList)
        adapter.notifyDataSetChanged()

    }

}