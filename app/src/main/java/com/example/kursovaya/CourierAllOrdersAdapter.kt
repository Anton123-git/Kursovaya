package com.example.kursovaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya.databinding.OrdItemBinding


class  CourierAllOrdersAdapter(private val listenerC: OnItemClickListenerC) : RecyclerView.Adapter<CourierAllOrdersAdapter.CAOrdsHolder>() {

    private val caordList = ArrayList<Ordres>()

    inner class CAOrdsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = OrdItemBinding.bind(item)

        fun bind(ordres: Ordres) = with(binding) {
            tvIdOrder.text = ordres.id_order
            tvPackInfo.text = ordres.package_items
            tvDate.text = ordres.date

            linInfo.setOnClickListener {
                listenerC.onInfoClickС(ordres)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CAOrdsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ord_item, parent, false)
        return CAOrdsHolder(view)
    }

    override fun getItemCount(): Int {
        return caordList.size
    }

    override fun onBindViewHolder(holder: CAOrdsHolder, position: Int) {
        holder.bind(caordList[position])
    }

    fun updateDataCAOrd(newList: List<Ordres>) {
        caordList.clear()
        caordList.addAll(newList)
        notifyDataSetChanged()
    }
}

interface OnItemClickListenerC {
    fun onInfoClickС(item: Ordres)
}
