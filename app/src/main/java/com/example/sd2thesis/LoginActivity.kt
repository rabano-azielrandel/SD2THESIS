package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user = FirebaseAuth.getInstance()

        /** Initializing Variables **/
        val login = findViewById<AppCompatButton>(R.id.btn_login)
        val noAcc = findViewById<TextView>(R.id.tv_no_acc)

        /** intent variable **/
        var intent : Intent?


        /** login
         * NOT FULLY DONE
         * PLEASE CONNECT IT TO FIREBASE **/
        login.setOnClickListener {

            val email = findViewById<TextInputLayout>(R.id.til_login_email).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.til_login_password).editText?.text.toString()



            if (email.isNotEmpty() && password.isNotEmpty()){
                user.signInWithEmailAndPassword(email,password).addOnCompleteListener{ mTask ->
                    if(mTask.isSuccessful){
                        Toast.makeText(
                            this,
                            "Login successful",
                            Toast.LENGTH_LONG
                        ).show()

                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    }else {
                        Toast.makeText(this, mTask.exception!!.message, Toast.LENGTH_LONG).show()
                    }
                }

            } else {
                Toast.makeText(this, "Email and/or Password cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        /** Redirect to Sign up **/
        noAcc.setOnClickListener {
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

}