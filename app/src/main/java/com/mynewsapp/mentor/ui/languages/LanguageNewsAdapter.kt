package com.mynewsapp.mentor.ui.languages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.mynewsapp.mentor.data.model.topHeadines.Article
import com.mynewsapp.mentor.databinding.TopHeadlineItemLayoutBinding
import com.mynewsapp.mentor.utils.Utils
import loadImage

class LanguageNewsAdapter(private val articleList: ArrayList<Article>) :
    RecyclerView.Adapter<LanguageNewsAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {

            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name

            binding.imageViewBanner.loadImage(article.imageUrl)

            itemView.setOnClickListener { Utils.openCustomTabUrl(it.context, article.url) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(articleList[position])
    }

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }

}