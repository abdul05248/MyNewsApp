package com.mynewsapp.mentor.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide

class Utils {

    companion object{

        fun openCustomTabUrl(context: Context, url:String){
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }

        fun loadImage(context: Context,imageView: ImageView, url: String){
            Glide.with(context)
                .load(url)
                .into(imageView)
        }

    }


}