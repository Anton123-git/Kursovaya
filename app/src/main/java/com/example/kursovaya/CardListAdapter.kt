package com.example.kursovaya

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CardListAdapter(context: Context, private val resource: Int, private val items: ArrayList<String>) :
    ArrayAdapter<String>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            val inflater = LayoutInflater.from(context)
            itemView = inflater.inflate(resource, parent, false)
        }

        val textView: TextView = itemView!!.findViewById(R.id.textView15)
        textView.text = items[position]

        val btnDel: ImageView = itemView.findViewById(R.id.btnDel)

        btnDel.setOnClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
        }

        return itemView
    }
}
