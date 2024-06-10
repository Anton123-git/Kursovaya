package com.example.kursovaya

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class STwoNewPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_new_pass)

        val editTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        val editTextPassword2 = findViewById<EditText>(R.id.editTextTextPassword2)
        val button = findViewById<Button>(R.id.btn_login)

        // Добавляем слушатели изменений для EditText
        editTextPassword.addTextChangedListener(textWatcher)
        editTextPassword2.addTextChangedListener(textWatcher)

        // Проверяем заполненность полей и устанавливаем начальное состояние кнопки
        checkFieldsAndEnableButton(editTextPassword, editTextPassword2, button)

        // Устанавливаем слушатель для кнопки (если нужно)
        button.setOnClickListener {
            startActivity(Intent(this, main::class.java))
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val editTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
            val editTextPassword2 = findViewById<EditText>(R.id.editTextTextPassword2)
            val button = findViewById<Button>(R.id.btn_login)
            checkFieldsAndEnableButton(editTextPassword, editTextPassword2, button)
        }
    }

    private fun checkFieldsAndEnableButton(
        editTextPassword: EditText,
        editTextPassword2: EditText,
        button: Button
    ) {
        val password1 = editTextPassword.text.toString()
        val password2 = editTextPassword2.text.toString()

        // Проверяем заполненность полей и устанавливаем состояние кнопки и ее стиль
        if (password1.isNotEmpty() && password2.isNotEmpty() && password1 == password2) {
            button.isEnabled = true
            button.setBackgroundResource(R.drawable.btn_next_style)
        } else {
            button.isEnabled = false
            button.setBackgroundResource(R.drawable.btn_signup_gray)
        }
    }
}
