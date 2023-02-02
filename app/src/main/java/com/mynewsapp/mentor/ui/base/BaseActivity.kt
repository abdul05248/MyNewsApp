package com.mynewsapp.mentor.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.mynewsapp.mentor.MyApplication
import com.mynewsapp.mentor.di.component.DaggerActivityComponent
import com.mynewsapp.mentor.di.module.ActivityModule
import javax.inject.Inject


open class BaseActivity<B : ViewBinding, V : ViewModel>(val bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity() {

    lateinit var binding: B

    @Inject
    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
    }

}