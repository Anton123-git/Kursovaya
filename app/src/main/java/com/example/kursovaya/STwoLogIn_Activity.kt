package com.example.kursovaya

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Global
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class STwoLogIn_Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_login)
        auth = Firebase.auth

        val logIn = findViewById<Button>(R.id.btn_login)

        logIn.setOnClickListener {
            val emaildata = findViewById<EditText>(R.id.editTextTextEmailAddress2)
            val passwordData = findViewById<EditText>(R.id.editTextTextPassword)

            val email = emaildata.text.toString()
            val password = passwordData.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Авторизация успешна, переход на главный экран
                        startActivity(Intent(this, main::class.java))
                    } else {
                        val transaction = supportFragmentManager.beginTransaction()
                        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                        transaction.replace(R.id.frameeror, Error_frag())
                        transaction.commit()

                        Handler(Looper.getMainLooper()).postDelayed({
                            supportFragmentManager.beginTransaction()
                                .remove(Error_frag())
                                .commit()
                        }, 15000) // 15 секунд
                    }
                }
        }

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            val emailFilled = emailEditText.text.isNotBlank()
            val passwordFilled = passwordEditText.text.isNotBlank()
            if (isChecked && emailFilled && passwordFilled) {
                logIn.isEnabled = true
                logIn.backgroundTintList = getColorStateList(R.color.FullLog)
            } else {
                logIn.isEnabled = false
                logIn.backgroundTintList = getColorStateList(R.color.EmptyLog)
            }
        }
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