package com.mynewsapp.mentor.utils

import com.mynewsapp.mentor.data.model.countries.Country
import com.mynewsapp.mentor.data.model.languages.Language

object AppConstant {

    const val COUNTRY = "us"

    val LANGUAGES = arrayListOf(

        Language("hi", "Hindi"),
        Language("en", "English"),
        Language("ar", "Arabic"),
        Language("fr", "French"),

    )

    val COUNTRIES = arrayListOf(

        Country("in", "India"),
        Country("us", "USA"),
        Country("sa", "Saudi Arabia"),
        Country("ca", "Canada"),

    )

}