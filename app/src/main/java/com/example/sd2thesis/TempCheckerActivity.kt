package com.example.sd2thesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//test
/*
import nltk.downloader.*
import nltk.tokenize.*
import nltk.corpus.*
*/

class TempCheckerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_checker)

       /* Downloader().download("punkt") // download the word tokenizer
        Downloader().download("stopwords") // download the stopwords

        val stopWords = stopwords.words("tagalog").toSet() // set of Tagalog stopwords

        fun checkSpelling(text: String): List<String> {
            // tokenize the words in the text
            val words = word_tokenize(text)

            // remove any stopwords and non-alphabetic characters
            val filteredWords = words.filter { it.isAlpha() && it.toLowerCase() !in stopWords }

            // spellcheck the remaining words using a spellchecker library (e.g. PySpellChecker)
            // Add code here

            return filteredWords
        }

        val text = "Ang mga tao ay nagtatrabaho sa opisina."
        val correctedText = checkSpelling(text)
        println(correctedText)*/
    }
}