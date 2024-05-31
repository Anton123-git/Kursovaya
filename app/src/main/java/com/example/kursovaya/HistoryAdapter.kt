package com.example.kursovaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya.databinding.HisItemBinding

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.HisHolder>() {

    val hisList = ArrayList<ItemHistory>()

    class HisHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = HisItemBinding.bind(item)
        fun bind (itemHistory: ItemHistory) = with(binding){
            tvTitle.text = itemHistory.title
            tvSumm.text = itemHistory.sum.toString()
            tvDate.text = itemHistory.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HisHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.his_item,parent, false)
        return HisHolder(view)
    }

    override fun getItemCount(): Int {
        return hisList.size
    }

    override fun onBindViewHolder(holder: HisHolder, position: Int) {
        holder.bind(hisList[position])
    }
}