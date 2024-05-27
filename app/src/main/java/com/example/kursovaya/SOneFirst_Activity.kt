package com.example.kursovaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SOneFirst_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sone_first)
    }

    fun onClickNextAct(view: View){

        val intent = Intent(this, SOneSecond_Activity::class.java)
        startActivity(intent)

    }
    fun onClickSkipSOne(view: View){

        val intent = Intent(this, session_four_wallet::class.java)
        startActivity(intent)

    }
}