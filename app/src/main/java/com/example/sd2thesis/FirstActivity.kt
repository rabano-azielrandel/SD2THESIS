package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if the user has already opened the app before
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val isFirstTime = prefs.getBoolean("isFirstTime", true)

        if (isFirstTime) {
            // If it's the first time, show the FirstActivity and set the flag to false
            prefs.edit().putBoolean("isFirstTime", false).apply()
            setContentView(R.layout.activity_first)

            val btn_getstarted = findViewById<ImageButton>(R.id.btn_getstarted)
            btn_getstarted.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finish this activity so that it won't show again
            }
        } else {
            // If it's not the first time, show the FirstActivity again
            setContentView(R.layout.activity_first)

            val btn_getstarted = findViewById<ImageButton>(R.id.btn_getstarted)
            btn_getstarted.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finish this activity so that it won't show again
            }
        }
    }
}

