package com.example.kursovaya

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya.databinding.HisItemBinding


class  HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HisHolder>() {

    private val hisList = ArrayList<ItemHistory>()

    class HisHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HisItemBinding.bind(item)

        fun bind(itemHistory: ItemHistory) = with(binding) {
            tvTitle.text = itemHistory.title
            val decimalFormat = DecimalFormat("#,###.00")
            val formattedSum = decimalFormat.format(itemHistory.sum.toDouble())
            tvSumm.text = "N$formattedSum"
            tvDate.text = itemHistory.date

            if (itemHistory.indexPay) {
                tvSumm.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
            } else {
                tvSumm.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
                tvCurr.text = "-"
                tvCurr.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HisHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.his_item, parent, false)
        return HisHolder(view)
    }

    override fun getItemCount(): Int {
        return hisList.size
    }

    override fun onBindViewHolder(holder: HisHolder, position: Int) {
        holder.bind(hisList[position])
    }

    fun updateData(newList: List<ItemHistory>) {
        hisList.clear()
        hisList.addAll(newList)
        notifyDataSetChanged()
    }
}
