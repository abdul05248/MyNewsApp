package com.mynewsapp.mentor.ui.languages

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mynewsapp.mentor.data.model.languages.Language
import com.mynewsapp.mentor.databinding.SourceItemLayoutBinding
import com.mynewsapp.mentor.ui.sources.NewsSourceActivity
import com.mynewsapp.mentor.ui.topHeadlines.TopHeadlinesActivity
import com.mynewsapp.mentor.utils.AppConstant


class LanguageAdapter(private val languageList: ArrayList<Language>) :
    RecyclerView.Adapter<LanguageAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: SourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(language: Language){

                binding.tvItem.text=language.name

                binding.tvItem.setOnClickListener{

                    it.context.startActivity(Intent(it.context, LanguageNewsActivity::class.java)
                        .putExtra(AppConstant.LANGUAGE, language.id))

                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        return DataViewHolder(SourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun getItemCount(): Int =languageList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(languageList[position])
    }

    fun addData(list: List<Language>){
        languageList.addAll(list)
    }
}




