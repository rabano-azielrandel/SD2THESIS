package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.StringTokenizer

class TempCheckerActivity : AppCompatActivity() {

    private var dbRef = FirebaseDatabase.getInstance().getReference("db")
    private val list : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_checker)

        /** Initializing variables **/
        val currText = findViewById<TextView>(R.id.tv_currentTextVal)
        val checkedText = findViewById<TextView>(R.id.tv_correctedTextVal)
        val returnMain = findViewById<AppCompatButton>(R.id.btn_return_to_main)
        val check = findViewById<AppCompatButton>(R.id.btn_check)

        val setTxt = intent.getStringExtra("text")


        /** setting the text from text editor to current text tv **/
        currText.text = setTxt

        dbRef.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (suggestSnapshot in snapshot.children) {
                    val suggestion = suggestSnapshot.key.toString()
                    list.add(suggestion)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        /** Check Button **/
        check.setOnClickListener {
            val text = currText.text.toString()

            if (text.isBlank()) {
                Toast.makeText(this, "Return to text editor and input your text!", Toast.LENGTH_SHORT).show()
            } else {
                val newTxt = spellCheck(text)
                checkedText.text = newTxt

                Toast.makeText(this, "corrected", Toast.LENGTH_SHORT).show()
            }
        }

        /** Return Button **/

        returnMain.setOnClickListener {
            val txt = checkedText.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("txt", txt)
            startActivity(intent)
        }
    }

    private fun spellCheck(text: String): String {
        /** get the list of words in the 'db' firebase dictionary **/

        var correctedTxt = getCorrectedText(text)

        return correctedTxt
    }

    private fun getCorrectedText(text: String): String {
        /** tokenizing the text as well as calculating the distance of the wrong
         * and the correct one **/
        val tokenizer = StringTokenizer(text)
        val correctedText = StringBuilder()

        while (tokenizer.hasMoreTokens()) {
            var word = tokenizer.nextToken()
            var closestWord = ""
            var minDistance = Int.MAX_VALUE

            for (dictionaryWord in list) {
                val distance = editDistance(word, dictionaryWord)
                if (distance < minDistance) {
                    closestWord = dictionaryWord
                    minDistance = distance
                }
            }
            correctedText.append(closestWord).append(" ")
        }

        return correctedText.toString().trim()
    }

    /** for calculating how much distance does the misspelled word in the dictionary word **/
    /** it is for calculating the like terms of misspelled to dictionary word **/
    /** we still need to use better algo for this such as Levenshtein algorithm **/
    private fun editDistance(word1: String, word2: String): Int {
        val m = word1.length
        val n = word2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        for (i in 0..m) {
            dp[i][0] = i
        }
        for (j in 0..n) {
            dp[0][j] = j
        }
        for (i in 1..m) {
            for (j in 1..n) {
                val cost = if (word1[i - 1] == word2[j - 1]) 0 else 1
                dp[i][j] = minOf(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost)
            }
        }
        return dp[m][n]
    }
}