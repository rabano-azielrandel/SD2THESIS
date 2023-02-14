package com.example.sd2thesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfile : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val first_Name = findViewById<EditText>(R.id.editFirstName)
        val last_Name = findViewById<EditText>(R.id.editLastName)
        val birthdate = findViewById<EditText>(R.id.editBirthday)
        val grade_Level = findViewById<EditText>(R.id.editGradeLevel)
        val schoolOrg = findViewById<EditText>(R.id.editSchool)
        val emailAddress = findViewById<EditText>(R.id.editEmail)
        val secQuestion = findViewById<EditText>(R.id.editQuestion)
        val secAnswer = findViewById<EditText>(R.id.editAnswer)
        val pass = findViewById<EditText>(R.id.editPassword)
        val confirmPassword = findViewById<EditText>(R.id.editConfirmPassword)

        // Step 1: Get a reference to the Firebase Realtime Database instance
        database = FirebaseDatabase.getInstance().reference

        // Step 2: Use the FirebaseAuth object to get the ID of the current user
        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser!!.uid

        // Step 3: Get a reference to the user's profile data in the Realtime Database using the ID
        val userRef = database.child("Profiles").child(userId)

        // Step 4: Retrieve the data using a ValueEventListener
        userRef.addListenerForSingleValueEvent( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // Retrieve the data from the snapshot
                    val firstName = snapshot.child("first_name").value as String
                    val lastName = snapshot.child("last_name").value as String
                    val birthday = snapshot.child("bithday").value as String
                    val gradeLevel = snapshot.child("grade_level").value as String
                    val school = snapshot.child("school").value as String
                    val email = snapshot.child("email_address").value as String
                    val question = snapshot.child("security_question").value as String
                    val answer = snapshot.child("answer").value as String
                    val password = snapshot.child("password").value as String

                    // Step 5: Display the data in the EditText views
                    first_Name.setText(firstName)
                    last_Name.setText(lastName)
                    birthdate.setText(birthday)
                    grade_Level.setText(gradeLevel)
                    schoolOrg.setText(school)
                    emailAddress.setText(email)
                    secQuestion.setText(question)
                    secAnswer.setText(answer)
                    pass.setText(password)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle any errors
            }

        })

    }
}