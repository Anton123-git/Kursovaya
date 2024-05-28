package com.example.kursovaya

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class STwoNewPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val login = findViewById<Button>(R.id.btn_login)
        login.setOnClickListener{
            startActivity(Intent(this, main::class.java))
        }
        setContentView(R.layout.activity_stwo_new_pass)
    }
}