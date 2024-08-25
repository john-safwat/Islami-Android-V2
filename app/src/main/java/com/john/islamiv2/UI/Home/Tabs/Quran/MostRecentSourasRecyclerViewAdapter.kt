package com.john.islamiv2.UI.Home.Tabs.Quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.john.islamiv2.Models.Sura
import com.john.islamiv2.databinding.ItemRecentCardBinding


class MostRecentSourasRecyclerViewAdapter(val surasList: List<Sura>) :
    RecyclerView.Adapter<MostRecentSourasRecyclerViewAdapter.MostRecentSurasViewHolder>() {

    inner class MostRecentSurasViewHolder(val viewBinding: ItemRecentCardBinding) :
        ViewHolder(viewBinding.root) {
        fun bind(sura: Sura, isLastIndex: Boolean) {
            viewBinding.txtCardArabicSuraTitle.text = sura.arabicTitle
            viewBinding.txtCardEnglishSuraTitle.text = sura.englishTitle
            "${sura.versesNumber} Verses".also { viewBinding.txtCardVersesNumber.text = it }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostRecentSurasViewHolder {
        val viewHolder = MostRecentSurasViewHolder(
            ItemRecentCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun getItemCount(): Int {
        return surasList.size
    }

    override fun onBindViewHolder(holder: MostRecentSurasViewHolder, position: Int) {
        holder.bind(surasList[position] ,position == surasList.size-1)
    }


}