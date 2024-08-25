package com.john.islamiv2.UI.Home.Tabs.Quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.INVISIBLE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.john.islamiv2.Models.Sura
import com.john.islamiv2.databinding.ItemSuraCardBinding

class SurasListRecyclerViewAdapter(private var surasList: MutableList<Sura>) :
    RecyclerView.Adapter<SurasListRecyclerViewAdapter.SuraCardViewHolder>() {

    inner class SuraCardViewHolder(val viewBinding: ItemSuraCardBinding) :
        ViewHolder(viewBinding.root) {
        fun bind(sura: Sura, lastIndex: Boolean) {
            viewBinding.txtSuraNumber.text = sura.id.toString()
            viewBinding.txtEnglishSuraTitle.text = sura.englishTitle
            viewBinding.txtArabicSuraTitle.text = sura.arabicTitle
            ("${sura.versesNumber} Verses").also { viewBinding.txtSuraVersesNumber.text = it }
            if (lastIndex) {
                viewBinding.divider.visibility = INVISIBLE
            } else {
                viewBinding.divider.visibility = VISIBLE
            }
            viewBinding.root.setOnClickListener { onItemClickListener?.onItemClick(sura) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraCardViewHolder {
        val viewHolder = SuraCardViewHolder(
            ItemSuraCardBinding.inflate(
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

    override fun onBindViewHolder(holder: SuraCardViewHolder, position: Int) {
        holder.bind(surasList[position], position == surasList.size - 1)
    }

    fun updateList(newList: MutableList<Sura>) {
        surasList = newList
        notifyDataSetChanged()
    }

    var onItemClickListener: OnItemClickListener? =null

    fun interface OnItemClickListener {
        fun onItemClick(sura: Sura)
    }

}