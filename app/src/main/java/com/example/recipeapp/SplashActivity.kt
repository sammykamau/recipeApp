package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val getStarted_btn: Button = findViewById(R.id.btnGetStarted)

        getStarted_btn.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

    }
}