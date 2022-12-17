package com.mynewsapp.mentor.ui.countries

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mynewsapp.mentor.data.model.countries.Country
import com.mynewsapp.mentor.databinding.SourceItemLayoutBinding
import com.mynewsapp.mentor.ui.sources.NewsSourceActivity


class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: SourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(country: Country){

                binding.tvItem.text=country.name

                binding.tvItem.setOnClickListener{

                    it.context.startActivity(Intent(it.context, NewsSourceActivity::class.java))
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        return DataViewHolder(SourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun getItemCount(): Int =countryList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

    fun addData(list: List<Country>){
        countryList.addAll(list)
    }
}




