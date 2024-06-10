package com.example.kursovaya

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid ?: ""
        val db = FirebaseDatabase.getInstance().getReference("cards/$userId")

        btnDel.setOnClickListener {
            val cardNumber = items[position]
            db.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (card in dataSnapshot.children) {
                        if (card.child("number").value == cardNumber) {
                            val cardId = card.key
                            db.child(cardId!!).removeValue()
                            items.removeAt(position)
                            notifyDataSetChanged()
                            break
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle the error
                }
            })
        }

        return itemView
    }
}