package com.mynewsapp.mentor.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.di.component.ActivityComponent
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import javax.inject.Inject


abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    lateinit var binding: VB

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setUpUi()
        setUpObserver()
    }

    private fun buildActivityComponent(): ActivityComponent {

        return DaggerActivityComponent.builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    }

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun getViewBinding(): VB

    protected abstract fun setUpUi()

    protected abstract fun setUpObserver()

}