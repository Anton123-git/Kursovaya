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

class admMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adm_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, admin_users())
            .commit()


        val users = findViewById<LinearLayout>(R.id.users)
        users.setOnClickListener {
            footerChange(1)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, admin_users())
                .commit()
        }


        val orders = findViewById<LinearLayout>(R.id.orders)
        orders.setOnClickListener {
            footerChange(2)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, Admin_AllOrders())
                .commit()
        }

    }



    fun footerChange(number: Int) {
        val order = findViewById<ImageView>(R.id.order)
        val user = findViewById<ImageView>(R.id.user)

        val userIdn = findViewById<ImageView>(R.id.imageViewHome)
        val orderIdn = findViewById<ImageView>(R.id.imageViewWallet)

        val ustTv = findViewById<TextView>(R.id.usertv)
        val ordrtv = findViewById<TextView>(R.id.ordertv)

        if (order != null && user != null && userIdn != null && orderIdn != null) {
            if (number == 1) {
                user.setImageResource(R.drawable.user_svgrepo_com_act)
                order.setImageResource(R.drawable.order_svgrepo_com)

                ustTv.setTextColor(Color.parseColor("#0560FA")) // Sets the text color to red
                ordrtv.setTextColor(Color.parseColor("#A7A7A7")) // Sets the text color to red

                userIdn.setImageResource(R.drawable.rectangle_act)
                orderIdn.setImageResource(R.drawable.rectangle_enable)

            } else if (number == 2) {
                user.setImageResource(R.drawable.user_svgrepo_com)
                order.setImageResource(R.drawable.order_svgrepo_com_act)

                ustTv.setTextColor(Color.parseColor("#A7A7A7")) // Sets the text color to red
                ordrtv.setTextColor(Color.parseColor("#0560FA"))

                userIdn.setImageResource(R.drawable.rectangle_enable)
                orderIdn.setImageResource(R.drawable.rectangle_act)

            }
        } else {
            // Handle the case where the views are null
            // For example, you can log an error or display a message
            println("Error: One or more views are null.")
        }
    }
}