package com.example.kursovaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class STwoLogIn_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_stwo_login)
    }

    fun onClickSignUp(view: View){
        val intent = Intent(this, STwoSignUp_Activity::class.java)
        startActivity(intent)
    }
    fun onClickForgotPass(view: View){
        val intent = Intent(this, STwoForgotPass_Activity::class.java)
        startActivity(intent)
    }
}