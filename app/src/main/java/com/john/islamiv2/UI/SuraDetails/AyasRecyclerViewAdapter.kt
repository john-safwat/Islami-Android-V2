package com.john.islamiv2.UI.SuraDetails


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.john.islamiv2.Models.Aya
import com.john.islamiv2.R
import com.john.islamiv2.databinding.ItemAyaCardBinding

class AyasRecyclerViewAdapter(private val ayas: List<Aya>, private var selectedAya: Int = 0) :
    Adapter<AyasRecyclerViewAdapter.AyaViewHolder>() {

    inner class AyaViewHolder(val viewBinding: ItemAyaCardBinding) : ViewHolder(viewBinding.root) {
        fun bind(aya: Aya, position: Int) {
            viewBinding.tvAya.text = aya.content
            viewBinding.cardAya.setCardBackgroundColor(
                ContextCompat.getColor(viewBinding.root.context , R.color.transparent)
            )
            viewBinding.tvAya.setTextColor(
                viewBinding.cardAya.context.getColor(R.color.brown)
            )
            if (aya.isSelected) {
                viewBinding.cardAya.setCardBackgroundColor(
                    ContextCompat.getColor(viewBinding.root.context , R.color.brown)

                )
                viewBinding.tvAya.setTextColor(
                    viewBinding.cardAya.context.getColor(R.color.black)
                )
            }
            viewBinding.root.setOnClickListener{
                changeSelectedIndex(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyaViewHolder {
        val viewBinding =
            ItemAyaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AyaViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return ayas.size
    }

    override fun onBindViewHolder(holder: AyaViewHolder, position: Int) {
        holder.bind(ayas[position] , position)
    }

    private fun changeSelectedIndex(position: Int){

        val oldSelectedAya = selectedAya
        selectedAya = position
        ayas[oldSelectedAya].isSelected = false
        ayas[selectedAya].isSelected = true
        notifyItemChanged(oldSelectedAya)
        notifyItemChanged(selectedAya)
    }

}