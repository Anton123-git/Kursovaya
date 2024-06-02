package com.example.kursovaya


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class STwoSignUp_Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_signup)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val signUpBtn2 = findViewById<Button>(R.id.btn_signup)
        val nameEditText = findViewById<EditText>(R.id.editTextText2)
        val phoneEditText = findViewById<EditText>(R.id.editTextPhone)
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword2)

        signUpBtn2.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Регистрация пользователя с помощью email и пароля в Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        // Сохранение данных пользователя в Firebase Realtime Database
                        val userData = User(name, phone, user?.uid)
                        database.child("users").child(user?.uid ?: "").setValue(userData)
                    } else {
                        // Обработка ошибок при регистрации
                        Log.e("Registration", "Registration failed", task.exception)
                    }
                }
        }


        val checkBox = findViewById<CheckBox>(R.id.checkBoxes)
        // НИКИТА ПРОВЕРЬ ЭТОТ КОД, НЕ РАБОТАЕТ ОКРАШИВАНИЕ КНОПКИ ПОСЛЕ ВВОДА И ЧЕКА ЧЕКБОКСА!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            val emailFilled = emailEditText.text.isNotBlank()
            val passwordFilled = passwordEditText.text.isNotBlank()
            val nameFilled = nameEditText.text.isNotBlank()
            val phoneFilled = phoneEditText.text.isNotBlank()

            if (isChecked && emailFilled && passwordFilled && nameFilled && phoneFilled) {
                signUpBtn2.isEnabled = true
                signUpBtn2.backgroundTintList = getColorStateList(R.color.FullLog)
            } else {
                signUpBtn2.isEnabled = false
                signUpBtn2.backgroundTintList = getColorStateList(R.color.EmptyLog)
            }
        }


        val signIn = findViewById<TextView>(R.id.signInBtn)
        signIn.setOnClickListener{
            startActivity(Intent(this, STwoLogIn_Activity::class.java))
        }


    }
    fun onClickLogIn(view: View){
        val intent = Intent(this, STwoLogIn_Activity::class.java)
        startActivity(intent)

    }
}