package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DictionaryActivity : AppCompatActivity() {

    //private var database = FirebaseDatabase.getInstance("https://sd2thesis-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private var dbRef = FirebaseDatabase.getInstance().getReference("db")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        /** Initializing Variables **/
        val searchBarDict = findViewById<EditText>(R.id.et_search_bar_dictionary)
        val searchBtnDict = findViewById<AppCompatButton>(R.id.btn_search_dictionary)
        val dictReturn = findViewById<AppCompatButton>(R.id.btn_dictionary_return)
        val word = findViewById<TextView>(R.id.tv_searched_word)
        val meaning = findViewById<TextView>(R.id.tv_searched_meaning)


        /** Redirecting to dashboard **/
        dictReturn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** Search Button **/
        searchBtnDict.setOnClickListener {
            if (TextUtils.isEmpty(searchBarDict.text.toString())){
                Toast.makeText(this, "Please input a word!", Toast.LENGTH_LONG).show()
            } else{
                dbRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val keyWord = searchBarDict.text.toString()
                        keyWord.also { word.text = it }

                        if (snapshot.child(keyWord).exists()){
                            snapshot.child(keyWord).value.toString().also { meaning.text = it }
                        }else{
                            Toast.makeText(this@DictionaryActivity, "No Result Found", Toast.LENGTH_LONG).show()
                            "The word is not yet registered".also { meaning.text = it }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@DictionaryActivity, "Fatal Error", Toast.LENGTH_LONG).show()
                    }
                })
            }


        }

    }
}