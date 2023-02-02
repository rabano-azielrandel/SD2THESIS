package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class SignupActivity : AppCompatActivity() {

    private lateinit var user: FirebaseAuth
    private lateinit var firebase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        user = FirebaseAuth.getInstance()

        /** Initializing Variables **/
        val createAcc = findViewById<AppCompatButton>(R.id.btn_create_acc)
        val haveAcc = findViewById<TextView>(R.id.tv_have_acc)
        val questionList = arrayOf(
            "What is your favorite color?",
            "What is your pet's name?",
            "What is your favorite food?"
        )
        val spinner = findViewById<Spinner>(R.id.spin_security_quest)
        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, questionList)

        spinner.adapter = arrayAdapter


        /** Creates Account then Redirect to login **/
        createAcc.setOnClickListener {
            registerUser()

        }


        /** Redirect to login immediately**/
        haveAcc.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun registerUser() {

        /** Initializing variables **/
        val email = findViewById<TextInputLayout>(R.id.til_email).editText?.text.toString()
        val password = findViewById<TextInputLayout>(R.id.til_signup_pass).editText?.text.toString()
        val fname = findViewById<TextInputLayout>(R.id.til_fname).editText?.text.toString()
        val lname = findViewById<TextInputLayout>(R.id.til_lname).editText?.text.toString()
        val bday = findViewById<TextInputLayout>(R.id.til_bday).editText?.text.toString()
        val gradeLvl = findViewById<TextInputLayout>(R.id.til_grade_lvl).editText?.text.toString()
        val school = findViewById<TextInputLayout>(R.id.til_school).editText?.text.toString()
        val confirmPass = findViewById<TextInputLayout>(R.id.til_signup_confirm_pass).editText?.text.toString()
        val question = findViewById<Spinner>(R.id.spin_security_quest).selectedItem.toString()
        val answer = findViewById<TextInputLayout>(R.id.til_security_answer).editText?.text.toString()

        /** runs if user details are complete**/
        if (fname.isNotEmpty() && lname.isNotEmpty() && bday.isNotEmpty() && gradeLvl.isNotEmpty() && school.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty() && answer.isNotEmpty()) {

            /** runs if password is the same with confirm password**/
            if (confirmPass == password) {

                /** Creating Users in Firebase**/
                user.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignupActivity()) { task ->

                        /** will redirect to login activity after successful user registration**/
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Sign up successful, please login to your account",
                                Toast.LENGTH_LONG
                            ).show()

                            /** Adding User Details to Firebase**/
                            /*
                            firebase = FirebaseDatabase.getInstance().getReference("Profiles")
                            val registeruser = Users(answer,bday, email, fname, grade_lvl, password, question, school, lname)
                            firebase.child(email).setValue(registeruser)*/

                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        /** toast for the built in constraints **/
                        else {
                            Toast.makeText(this, task.exception!!.message, Toast.LENGTH_LONG).show()
                        }
                    }

            }
            /** If password and confirm password are not the same**/
            else {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_LONG).show()
            }
        }

        /** If the user details are not complete**/
        else {
            Toast.makeText(
                this,
                "User Details are not complete. Please enter your details.",
                Toast.LENGTH_LONG
            ).show()
        }

    }

}

