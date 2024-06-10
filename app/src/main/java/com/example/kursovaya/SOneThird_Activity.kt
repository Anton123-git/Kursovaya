package com.example.kursovaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SOneThird_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sone_third)
    }

    fun onClickNextAct(view: View){
        val intent = Intent(this, sign_up::class.java)
        startActivity(intent)

    }

    fun onClickLogIn(view: View){
        val intent = Intent(this, STwoLogIn_Activity::class.java)
        startActivity(intent)

    }

}