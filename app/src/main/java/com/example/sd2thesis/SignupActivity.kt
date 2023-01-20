package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        /** Initializing Variables **/
        val createAcc = findViewById<AppCompatButton>(R.id.btn_create_acc)
        val haveAcc = findViewById<TextView>(R.id.tv_have_acc)

        /** Creates Account then Redirect to login **/
        createAcc.setOnClickListener {
            Toast.makeText(this, "Create Account is not available at this moment",
                            Toast.LENGTH_LONG).show()

            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        /** Redirect to login immediately**/
        haveAcc.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}