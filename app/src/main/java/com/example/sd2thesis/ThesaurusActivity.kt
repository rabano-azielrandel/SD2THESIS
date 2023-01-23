package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ThesaurusActivity : AppCompatActivity() {

    private var sRef = FirebaseDatabase.getInstance().getReference("Synonyms")
    private var aRef = FirebaseDatabase.getInstance().getReference("Antonyms")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thesaurus)

        /** Initializing Variables **/
        val searchBar = findViewById<EditText>(R.id.et_search_bar_thesaurus)
        val searchBtn = findViewById<AppCompatButton>(R.id.btn_search_thesaurus)
        val wordThesaurus = findViewById<TextView>(R.id.tv_searched_word_thesaurus)
        val antonyms = findViewById<TextView>(R.id.tv_antonyms)
        val synonyms = findViewById<TextView>(R.id.tv_synonyms)
        val returnToDictionary = findViewById<AppCompatButton>(R.id.btn_return_to_dictionary)

        /** Redirecting to dictionary **/
        returnToDictionary.setOnClickListener{
            val intent = Intent(this, DictionaryActivity::class.java)
            startActivity(intent)
        }

        /** Search Button **/
        searchBtn.setOnClickListener {
            if (TextUtils.isEmpty(searchBar.text.toString())){
                Toast.makeText(this, "Please input a word!", Toast.LENGTH_LONG).show()
            } else{
                aRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val keyWord = searchBar.text.toString()
                        keyWord.also { wordThesaurus.text = it }

                        if (snapshot.child(keyWord).exists()){
                            snapshot.child(keyWord).value.toString().also { antonyms.text = it }
                        }else{
                            "The word is not yet registered".also { antonyms.text = it }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@ThesaurusActivity, "Fatal Error", Toast.LENGTH_SHORT).show()
                    }
                })

                sRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val keyWord = searchBar.text.toString()
                        keyWord.also { wordThesaurus.text = it }

                        if (snapshot.child(keyWord).exists()){
                            snapshot.child(keyWord).value.toString().also { synonyms.text = it }
                        }else{
                            "The word is not yet registered".also { synonyms.text = it }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@ThesaurusActivity, "Fatal Error", Toast.LENGTH_SHORT).show()
                    }
                })

            }

        }
    }
}