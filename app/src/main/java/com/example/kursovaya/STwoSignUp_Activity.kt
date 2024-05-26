package com.example.kursovaya

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity



class STwoSignUp_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_stwo_signup)

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val text = "By ticking this box, you agree to our Terms and conditions and private policy"

        // Устанавливаем цвет только для куска текста "Мой"
        val spannable = SpannableString(text)
        val startIndex = 38
        val endIndex = 77
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#FFCF48")),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        checkBox.text = spannable
    }

    fun onClickNextAct(view: View){
        val intent = Intent(this, STwoLogIn_Activity::class.java)
        startActivity(intent)

    }
}