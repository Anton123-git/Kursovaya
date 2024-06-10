package com.example.kursovaya

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class STwoForgotPass_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_forgotpass)

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val sendOTPButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_signup)

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendOTPButton.isEnabled = android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        sendOTPButton.setOnClickListener {
            val intent = Intent(this, STwoOTPVerif_Activity::class.java)
            startActivity(intent)
            // Здесь можно добавить код для отправки OTP на указанную почту
        }

        fun onClickSignUp(view: View) {
            val intent = Intent(this, sign_up::class.java)
            startActivity(intent)
        }
    }
}