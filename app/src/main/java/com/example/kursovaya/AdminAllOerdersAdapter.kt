package com.example.kursovaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya.databinding.AdordItemBinding
import com.example.kursovaya.databinding.OrdItemBinding
import com.google.api.ResourceProto.resource

class  AdminAllOerdersAdapter : RecyclerView.Adapter<AdminAllOerdersAdapter.AAOrdsHolder>() {

    private val aaordList = ArrayList<AdminAllOrderItem>()

    class AAOrdsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = AdordItemBinding.bind(item)

        fun bind(aaorderItem: AdminAllOrderItem) = with(binding) {
            tvIdOrder.text = aaorderItem.id_order.toString()
            tvPackageInfo.text = aaorderItem.package_items
            tvDate.text = aaorderItem.date

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AAOrdsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ord_item, parent, false)
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
