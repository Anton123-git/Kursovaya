package com.example.kursovaya


import android.content.Intent
import android.content.res.ColorStateList
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

class sign_up : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val signUpBtn2 = findViewById<Button>(R.id.btn_signup)
        val nameEditText = findViewById<EditText>(R.id.editTextText2)
        val phoneEditText = findViewById<EditText>(R.id.editTextPhone)
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword2)

        signUpBtn2.isEnabled = false
        signUpBtn2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.EmptyLog))

        val checkBox = findViewById<CheckBox>(R.id.checkBoxes)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            val emailFilled = emailEditText.text.isNotBlank()
            val passwordFilled = passwordEditText.text.isNotBlank()
            val nameFilled = nameEditText.text.isNotBlank()
            val phoneFilled = phoneEditText.text.isNotBlank()

            if (isChecked && emailFilled && passwordFilled && nameFilled && phoneFilled) {
                signUpBtn2.isEnabled = true
                signUpBtn2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.FullLog))
            } else {
                signUpBtn2.isEnabled = false
                signUpBtn2.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.EmptyLog))
            }
        }

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
                        val userData = User(name, phone, user?.uid, status = 1)
                        database.child("users").child(user?.uid ?: "").setValue(userData)
                        // Создаем новый узел для платежей
                        val paymentsRef = database.child("payments").child(user?.uid ?: "")
                        // Создаем новый платеж
                        val payment = Payment(
                            index_pay = 1,
                            amount = 100.0,
                            title = "Payment 1",
                            date = "2024-06-04",
                            uid = user?.uid ?: ""
                        )
                        paymentsRef.push().setValue(payment)
                    } else {
                        // Обработка ошибок при регистрации
                        Log.e("Registration", "Registration failed", task.exception)
                    }
                }
        }

        val signIn = findViewById<TextView>(R.id.signInBtn)
        signIn.setOnClickListener {
            startActivity(Intent(this, STwoLogIn_Activity::class.java))
        }
    }
}