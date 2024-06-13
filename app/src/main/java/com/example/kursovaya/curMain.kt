package com.example.kursovaya

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class curMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cur_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, Courier_orders())
            .commit()


        val orders = findViewById<LinearLayout>(R.id.orders)
        orders.setOnClickListener {
            footerChange(1)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, Courier_orders())
                .commit()
        }


        val track = findViewById<LinearLayout>(R.id.tracking)
        track.setOnClickListener {
            footerChange(2)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, track())
                .commit()
        }

    }



    fun footerChange(number: Int) {
        val order = findViewById<ImageView>(R.id.order)
        val track = findViewById<ImageView>(R.id.track)

        val orderIdn = findViewById<ImageView>(R.id.imageViewOrder)
        val trackIdn = findViewById<ImageView>(R.id.imageViewTrack)

        val trkTv = findViewById<TextView>(R.id.tracktv)
        val ordrtv = findViewById<TextView>(R.id.ordertv)

        if (order != null && track != null && trackIdn != null && orderIdn != null) {
            if (number == 1) {
                order.setImageResource(R.drawable.order_svgrepo_com_act)
                track.setImageResource(R.drawable.track_svgrepo_com)

                ordrtv.setTextColor(Color.parseColor("#0560FA")) // Sets the text color to red
                trkTv.setTextColor(Color.parseColor("#A7A7A7")) // Sets the text color to red

                orderIdn.setImageResource(R.drawable.rectangle_act)
                trackIdn.setImageResource(R.drawable.rectangle_enable)

            } else if (number == 2) {
                track.setImageResource(R.drawable.track_svgrepo_com_act)
                order.setImageResource(R.drawable.order_svgrepo_com)

                trkTv.setTextColor(Color.parseColor("#0560FA")) // Sets the text color to red
                ordrtv.setTextColor(Color.parseColor("#A7A7A7"))

                trackIdn.setImageResource(R.drawable.rectangle_act)
                orderIdn.setImageResource(R.drawable.rectangle_enable)

            }
        } else {
            // Handle the case where the views are null
            // For example, you can log an error or display a message
            println("Error: One or more views are null.")
        }
    }
}