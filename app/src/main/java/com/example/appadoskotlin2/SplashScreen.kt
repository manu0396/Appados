package com.example.appadoskotlin2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

    //TODO("Solucionar navigation.xml separando la navegacion entre activities y fragments.")

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val miIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(miIntent)
        }, 3000)
    }
}
