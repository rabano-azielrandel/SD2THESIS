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

/** DICTIONARY SRC
 * https://github.com/luisligunas/pinoy-dictionary-scraper/blob/main/Scraped%20Data/Dictionaries/tagalog_dictionary.json
 * luisligunas/pinoy-dictionary-scraper **/
class DictionaryActivity : AppCompatActivity() {

    private var dbRef = FirebaseDatabase.getInstance().getReference("Dictionary")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)

        /** Initializing Variables **/
        val searchBarDict = findViewById<EditText>(R.id.et_search_bar_dictionary)
        val searchBtnDict = findViewById<AppCompatButton>(R.id.btn_search_dictionary)
        val dictReturn = findViewById<AppCompatButton>(R.id.btn_dictionary_return)
        val word = findViewById<TextView>(R.id.tv_searched_word)
        val meaning = findViewById<TextView>(R.id.tv_searched_meaning)
        val thesaurus = findViewById<AppCompatButton>(R.id.btn_go_thesaurus)


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
                            "The word is not yet registered".also { meaning.text = it }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@DictionaryActivity, "Fatal Error", Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }

        /** Thesaurus switcher**/
        thesaurus.setOnClickListener {
            val intent = Intent(this, ThesaurusActivity::class.java)
            startActivity(intent)
        }
    }
}