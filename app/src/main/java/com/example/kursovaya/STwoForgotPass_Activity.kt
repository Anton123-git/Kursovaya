package com.example.kursovaya

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class STwoForgotPass_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_forgotpass)

        val sendOTPButton = findViewById<AppCompatButton>(R.id.btn_signup)
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress2)

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    sendOTPButton.isEnabled = true
                    sendOTPButton.setBackgroundResource(R.drawable.btn_next_style)
                } else {
                    sendOTPButton.isEnabled = false
                    sendOTPButton.setBackgroundResource(R.drawable.btn_signup_gray)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sendOTPButton.setOnClickListener {
            val intent = Intent(this, STwoOTPVerif_Activity::class.java)
            startActivity(intent)
        }

        fun onClickSignUp(view: View) {
            val intent = Intent(this, sign_up::class.java)
            startActivity(intent)
        }
    }
}
