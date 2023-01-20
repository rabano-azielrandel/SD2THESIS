package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /** Initializing Variables **/
        val forgetPass = findViewById<TextView>(R.id.tv_forget_pass)
        val login = findViewById<AppCompatButton>(R.id.btn_login)
        val noAcc = findViewById<TextView>(R.id.tv_no_acc)

        /** intent variable **/
        var intent : Intent?

        /** Forget Pass **/
        forgetPass.setOnClickListener {
            Toast.makeText(this, "available next patch", Toast.LENGTH_LONG).show()
        }

        /** login
         * NOT FULLY DONE
         * PLEASE CONNECT IT TO FIREBASE **/
        login.setOnClickListener {
            intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** Redirect to Sign up **/
        noAcc.setOnClickListener {
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}