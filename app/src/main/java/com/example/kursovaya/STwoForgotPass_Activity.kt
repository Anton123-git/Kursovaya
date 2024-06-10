package com.example.kursovaya
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class STwoForgotPass_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_forgotpass)


        val sendOTPButton = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btn_signup)


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