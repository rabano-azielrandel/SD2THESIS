package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class TempCheckerActivity : AppCompatActivity() {

    private var dbRef = FirebaseDatabase.getInstance().getReference("Dictionary")
    private val words = mutableListOf<String>()

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

        /** For spell checker list of words **/
        val inputStream = resources.openRawResource(R.raw.wordset)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))

        bufferedReader.forEachLine {
            words.add(it)
        }

        bufferedReader.close()

        /** Check Button **/
        check.setOnClickListener {
            val text = currText.text.toString()

            val tokenizer = StringTokenizer(text, " ")
            var correctedText = ""

            while (tokenizer.hasMoreTokens()) {
                val token = tokenizer.nextToken()
                if (words.contains(token)) {
                    correctedText += "$token "
                } else {
                    val correctedToken = getCorrectedText(token)
                    correctedText += "$correctedToken "
                }
            }

            //Log.d("DEBUG", "Corrected text: \"$correctedText\"") // Debug print statement

            if (correctedText.trim() == text.trim()) {
                Toast.makeText(this, "Nothing to correct", Toast.LENGTH_SHORT).show()
            } else {
                checkedText.text = correctedText.trim()
                Toast.makeText(this, "Corrected", Toast.LENGTH_SHORT).show()
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

    private fun getCorrectedText(text: String): String {
        /** tokenizing the text as well as calculating the distance of the wrong
         * and the correct one **/
        val word = text
        var closestWord = ""
        var minDistance = Int.MAX_VALUE

        for (dictionaryWord in words) {
            val distance = editDistance(word, dictionaryWord)
            if (distance < minDistance) {
                closestWord = dictionaryWord
                minDistance = distance
            }
        }

        return closestWord
    }

    /** for calculating how much distance does the misspelled word in the dictionary word **/
    /** it is for calculating the like terms of misspelled to dictionary word **/
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
                val substitutionCost = if (word1[i - 1] == word2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1, // deletion
                    dp[i][j - 1] + 1, // insertion
                    dp[i - 1][j - 1] + substitutionCost // substitution
                )
            }
        }

        return dp[m][n]
    }

    data class Word(
        val definition: String = "",
        val language: String = "",
        val link: String = "",
        val word: String = ""
    )
}