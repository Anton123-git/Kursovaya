package com.example.kursovaya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya.databinding.OrdersItemsBinding


class  OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrdHolder>() {

    private val ordList = ArrayList<OrderItem>()

    class OrdHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = OrdersItemsBinding.bind(item)

        fun bind(orderItem: OrderItem) = with(binding) {
            tvNameOrder.text = orderItem.nameOrder
            tvTitle.text = orderItem.title
            tvDate.text = orderItem.date

            when (orderItem.orderStatus) {
                1 -> {
                    tvNameOrder.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
                }
                2 -> {
                    tvNameOrder.setTextColor(ContextCompat.getColor(itemView.context, R.color.gold))
                }
                3 -> {
                    tvNameOrder.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.orders_items, parent, false)
        return OrdHolder(view)
    }

    override fun getItemCount(): Int {
        return ordList.size
    }

    override fun onBindViewHolder(holder: OrdHolder, position: Int) {
        holder.bind(ordList[position])
    }

    fun updateDataOrd(newList: List<OrderItem>) {
        ordList.clear()
        ordList.addAll(newList)
        notifyDataSetChanged()
    }
}
