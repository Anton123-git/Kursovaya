package com.example.kursovaya


import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class STwoLogIn_Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stwo_login)
        auth = Firebase.auth

        val logIn = findViewById<Button>(R.id.btn_login)

        logIn.isEnabled = false
        logIn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.EmptyLog))

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            val emailFilled = emailEditText.text.isNotBlank()
            val passwordFilled = passwordEditText.text.isNotBlank()
            if (isChecked && emailFilled && passwordFilled) {
                logIn.isEnabled = true
                logIn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.FullLog))
            } else {
                logIn.isEnabled = false
                logIn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.EmptyLog))
            }
        }

        logIn.setOnClickListener {
            val emaildata = findViewById<EditText>(R.id.editTextTextEmailAddress2)
            val passwordData = findViewById<EditText>(R.id.editTextTextPassword)

            val email = emaildata.text.toString()
            val password = passwordData.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        if (currentUser != null) {
                            val database = FirebaseDatabase.getInstance().getReference("users")
                            database.child(currentUser.uid).addListenerForSingleValueEvent(object :
                                ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    val user = dataSnapshot.getValue(User::class.java)
                                    if (user != null) {
                                        val userState = user.status
                                        if(userState == 1){
                                            userAct()
                                        } else if(userState == 2){
                                            admAct()
                                        } else {
                                            // Handle the case where the user's status is neither 1 nor 2
                                        }
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Log.e("firebase", "Error getting data")
                                }
                            })
                        }
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
    }

    fun userAct(){
        val intent = Intent(this, main::class.java)
        startActivity(intent)
    }

    fun admAct(){
        val intent = Intent(this, admMain::class.java)
        startActivity(intent)
    }

    fun onClickSignUp(view: View){
        val intent = Intent(this, sign_up::class.java)
        startActivity(intent)
    }
    fun onClickForgotPass(view: View){
        val intent = Intent(this, STwoForgotPass_Activity::class.java)
        startActivity(intent)
    }
}