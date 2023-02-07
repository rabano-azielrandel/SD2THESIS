package com.example.sd2thesis

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        val searchBarDict = findViewById<AutoCompleteTextView>(R.id.et_search_bar_dictionary)
        val searchBtnDict = findViewById<AppCompatButton>(R.id.btn_search_dictionary)
        val dictReturn = findViewById<AppCompatButton>(R.id.btn_dictionary_return)
        val word = findViewById<TextView>(R.id.tv_searched_word)
        val meaning = findViewById<TextView>(R.id.tv_searched_meaning)
        val thesaurus = findViewById<AppCompatButton>(R.id.btn_go_thesaurus)

        val keys = mutableListOf<String>()
        val autoComplete = ArrayAdapter(this, android.R.layout.simple_list_item_1, keys)
        searchBarDict.setAdapter(autoComplete)

        /** Redirecting to dashboard **/
        dictReturn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** Storing the data in firebase to list so that we can have autocomplete **/
        keys.clear()
        autoComplete.clear()
        dbRef.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (childSnapshot in snapshot.children) {
                    val childData = childSnapshot.getValue(Snap::class.java)
                    keys.add(childData?.word ?: continue)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        /** Search Button **/
        searchBtnDict.setOnClickListener {

            if (TextUtils.isEmpty(searchBarDict.text.toString())){
                Toast.makeText(this, "Please input a word!", Toast.LENGTH_LONG).show()
            } else{
                dbRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val keyWord = searchBarDict.text.toString()
                        keyWord.also { word.text = it }

                        dataSnapshot.children.forEach { childSnapshot ->
                            val childData = childSnapshot.getValue(Snap::class.java)
                            if (childData?.word == keyWord) {
                                val definition = childData.definition
                                meaning.text = definition
                            }
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

    data class Snap(
        val definition: String = "",
        val language: String = "",
        val link: String = "",
        val word: String = ""
    )
}