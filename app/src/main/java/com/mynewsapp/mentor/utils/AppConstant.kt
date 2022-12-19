package com.mynewsapp.mentor.utils

import com.mynewsapp.mentor.data.model.countries.Country
import com.mynewsapp.mentor.data.model.languages.Language

object AppConstant {

    const val LANGUAGE = "LANGUAGE"
    const val COUNTRY = "COUNTRY"

    const val COUNTRY_US = "us"

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

    const val API_KEY = "7502ab9197514f9792f796df2b3c35d7"

}