package com.example.kursovaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class STwoForgotPass_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_stwo_forgotpass)

        fun onClickSignUp(view: View){
            val intent = Intent(this, STwoSignUp_Activity::class.java)
            startActivity(intent)
        }

    }
}