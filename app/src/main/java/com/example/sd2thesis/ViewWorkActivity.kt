package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class ViewWorkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_work)

        /** Initializing Variables **/
        val viewWorkReturn = findViewById<AppCompatButton>(R.id.btn_view_work_return)


        /** Redirecting to dashboard **/
        viewWorkReturn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}