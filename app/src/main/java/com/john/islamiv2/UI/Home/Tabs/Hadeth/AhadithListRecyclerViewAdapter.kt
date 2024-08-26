package com.john.islamiv2.UI.Home.Tabs.Hadeth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.john.islamiv2.Models.Hadith
import com.john.islamiv2.databinding.ItemHadithCardBinding

class AhadithListRecyclerViewAdapter(private var ahadithList:MutableList<Hadith>) : Adapter<AhadithListRecyclerViewAdapter.ViewHolder>(){
    inner class ViewHolder(val viewBinding : ItemHadithCardBinding):RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(hadith: Hadith) {
            viewBinding.tvHadithTitle.text = hadith.title
            viewBinding.tvHadithContent.text = hadith.content
            viewBinding.root.setOnClickListener {
                onItemClickListener?.onItemClick(hadith)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemHadithCardBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return ahadithList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ahadithList[position])
    }

    fun updateList(newList:MutableList<Hadith>){
        ahadithList = newList
        notifyDataSetChanged()
    }

    fun interface OnItemClickListener{
        fun onItemClick(hadith: Hadith)
    }

    var onItemClickListener:OnItemClickListener? = null
}