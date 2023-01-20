package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton

class DictionaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        /** Initializing Variables **/
        val searchBarDict = findViewById<EditText>(R.id.et_search_bar_dictionary)
        val searchBtnDict = findViewById<AppCompatButton>(R.id.btn_search_dictionary)
        val dictReturn = findViewById<AppCompatButton>(R.id.btn_dictionary_return)


        /** Redirecting to dashboard **/
        dictReturn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** Search Button **/
        searchBtnDict.setOnClickListener {
            Toast.makeText(this, "available next patch", Toast.LENGTH_LONG).show()
        }

    }
}