package com.example.kursovaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya.databinding.AdordItemBinding

class AdminAllOerdersAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<AdminAllOerdersAdapter.AAOrdsHolder>() {

    private val aaordList = ArrayList<AdminAllOrderItem>()

    inner class AAOrdsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = AdordItemBinding.bind(item)

        fun bind(aaorderItem: AdminAllOrderItem) = with(binding) {
            tvIdOrder.text = aaorderItem.id_order
            tvPackageInfo.text = aaorderItem.package_items
            tvDate.text = aaorderItem.date

            imgInfo.setOnClickListener {
                listener.onInfoClick(aaorderItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AAOrdsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adord_item, parent, false)
        return AAOrdsHolder(view)
    }

    override fun getItemCount(): Int {
        return aaordList.size
    }

    override fun onBindViewHolder(holder: AAOrdsHolder, position: Int) {
        holder.bind(aaordList[position])
    }

    fun updateDataAAOrd(newList: List<AdminAllOrderItem>) {
        aaordList.clear()
        aaordList.addAll(newList)
        notifyDataSetChanged()
    }
}


interface OnItemClickListener {
    fun onInfoClick(item: AdminAllOrderItem)
}



