package com.mynewsapp.mentor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mynewsapp.mentor.databinding.ActivityMainBinding
import com.mynewsapp.mentor.ui.countries.CountryActivity
import com.mynewsapp.mentor.ui.languages.LanguageActivity
import com.mynewsapp.mentor.ui.search.SearchActivity
import com.mynewsapp.mentor.ui.sources.NewsSourceActivity
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()

    }

    private fun setListeners() {

        binding.tvTopHeadlines.setOnClickListener(this)
        binding.tvNewsSources.setOnClickListener(this)
        binding.tvCountries.setOnClickListener(this)
        binding.tvLanguages.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {

        when(p0?.id){

            binding.tvTopHeadlines.id-> startActivity(Intent(this, TopHeadlinesActivity::class.java))
            binding.tvNewsSources.id-> startActivity(Intent(this, NewsSourceActivity::class.java))
            binding.tvCountries.id-> startActivity(Intent(this, CountryActivity::class.java))
            binding.tvLanguages.id-> startActivity(Intent(this, LanguageActivity::class.java))
            binding.tvSearch.id-> startActivity(Intent(this, SearchActivity::class.java))

        }

    }
}