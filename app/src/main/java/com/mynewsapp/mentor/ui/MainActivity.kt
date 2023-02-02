package com.mynewsapp.mentor.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.mynewsapp.mentor.databinding.ActivityMainBinding
import com.mynewsapp.mentor.ui.base.BaseActivity
import com.mynewsapp.mentor.ui.countries.CountryActivity
import com.mynewsapp.mentor.ui.languages.LanguageActivity
import com.mynewsapp.mentor.ui.search.SearchActivity
import com.mynewsapp.mentor.ui.sources.NewsSourceActivity
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesActivity

//Ask$ there is no viewmodel for this activity what should I pass?
class MainActivity : BaseActivity<ActivityMainBinding,
        ViewModel>(ActivityMainBinding::inflate), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()

    }

    private fun setListeners() {

        binding.tvTopHeadlines.setOnClickListener(this)
        binding.tvNewsSources.setOnClickListener(this)
        binding.tvCountries.setOnClickListener(this)
        binding.tvLanguages.setOnClickListener(this)
        binding.tvSearch.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        when (p0?.id) {

            binding.tvTopHeadlines.id -> startActivity(
                Intent(
                    this,
                    TopHeadlinesActivity::class.java
                )
            )
            binding.tvNewsSources.id -> startActivity(Intent(this, NewsSourceActivity::class.java))
            binding.tvCountries.id -> startActivity(Intent(this, CountryActivity::class.java))
            binding.tvLanguages.id -> startActivity(Intent(this, LanguageActivity::class.java))
            binding.tvSearch.id -> startActivity(Intent(this, SearchActivity::class.java))

        }

    }
}