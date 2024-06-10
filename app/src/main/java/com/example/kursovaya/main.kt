package com.example.kursovaya

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main)
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, home())
            .commit()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val home = findViewById<ImageButton>(R.id.home)
        home.setOnClickListener {
            footerChange(1)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, home())
                .commit()
        }



        val wallet = findViewById<ImageButton>(R.id.wallet)
        wallet.setOnClickListener {
            footerChange(2)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, session_four_wallet())
                .commit()
        }


        val track = findViewById<ImageButton>(R.id.track)
        track.setOnClickListener {
            footerChange(3)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, send_a_package())
                .commit()
        }

        val profile = findViewById<ImageButton>(R.id.profile)
        profile.setOnClickListener {
            footerChange(4)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame, Profile())
                .commit()
        }

    }


    fun footerChange(number:Int){
        val wallet = findViewById<ImageButton>(R.id.wallet)
        val home = findViewById<ImageButton>(R.id.home)
        val track = findViewById<ImageButton>(R.id.track)
        val profile = findViewById<ImageButton>(R.id.profile)


        val homeIdn = findViewById<ImageView>(R.id.imageViewHome)
        val walletIdn = findViewById<ImageView>(R.id.imageViewWallet)
        val trackIdn = findViewById<ImageView>(R.id.imageViewTrack)
        val profileIdn = findViewById<ImageView>(R.id.imageViewProfile)
        if(number == 1){
            wallet.setImageResource(R.drawable.wallet)
            home.setImageResource(R.drawable.home_act)
            track.setImageResource(R.drawable.track)
            profile.setImageResource(R.drawable.profile)


            walletIdn.setImageResource(R.drawable.rectangle_enable)
            homeIdn.setImageResource(R.drawable.rectangle_act)
            trackIdn.setImageResource(R.drawable.rectangle_enable)
            profileIdn.setImageResource(R.drawable.rectangle_enable)
        }else if(number == 2){
            wallet.setImageResource(R.drawable.wallet_act)
            home.setImageResource(R.drawable.home)
            track.setImageResource(R.drawable.track)
            profile.setImageResource(R.drawable.profile)


            walletIdn.setImageResource(R.drawable.rectangle_act)
            homeIdn.setImageResource(R.drawable.rectangle_enable)
            trackIdn.setImageResource(R.drawable.rectangle_enable)
            profileIdn.setImageResource(R.drawable.rectangle_enable)
        }else if(number == 3){
            wallet.setImageResource(R.drawable.wallet)
            home.setImageResource(R.drawable.home)
            track.setImageResource(R.drawable.track_act)
            profile.setImageResource(R.drawable.profile)


            walletIdn.setImageResource(R.drawable.rectangle_enable)
            homeIdn.setImageResource(R.drawable.rectangle_enable)
            trackIdn.setImageResource(R.drawable.rectangle_act)
            profileIdn.setImageResource(R.drawable.rectangle_enable)
        }else if(number == 4){
            wallet.setImageResource(R.drawable.wallet)
            home.setImageResource(R.drawable.home)
            track.setImageResource(R.drawable.track)
            profile.setImageResource(R.drawable.profile_act)


            walletIdn.setImageResource(R.drawable.rectangle_enable)
            homeIdn.setImageResource(R.drawable.rectangle_enable)
            trackIdn.setImageResource(R.drawable.rectangle_enable)
            profileIdn.setImageResource(R.drawable.rectangle_act)
        }
    }
}