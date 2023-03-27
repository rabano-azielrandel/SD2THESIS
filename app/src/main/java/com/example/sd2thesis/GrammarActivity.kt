package com.example.sd2thesis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class GrammarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar)

        val back = findViewById<AppCompatButton>(R.id.btn_return_main)
        val sentence = findViewById<TextView>(R.id.sentence_container)
        val sentencePattern = findViewById<TextView>(R.id.sentence_pattern_container)
        val suggestedPattern = findViewById<TextView>(R.id.suggested_pattern_container)
        val suggestedSentence = findViewById<TextView>(R.id.suggested_sentence_container)

        /** get the text **/
        val setTxt = intent.getStringExtra("text")

        /** setting the thrown text as sentence to que **/
        sentence.text = setTxt

        /**go back to text editor**/
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /** creating 16 list to serve as POS TAGGER **/
        val wordLists = mutableMapOf<String, List<String>>()

        val fileNames = listOf(
            "expression", "exclamation", "adverb", "prefix", "interrogative", "verb", "infinitive",
            "idiomatic", "comparative", "colloquial", "noun", "grammar", "adjective", "preposition",
            "conjunction", "interjection"
        )

        val resources = resources
        val packageName = packageName

        for (fileName in fileNames) {
            val resourceId = resources.getIdentifier(fileName, "raw", packageName)
            val inputStream = resources.openRawResource(resourceId)
            val elements = inputStream.bufferedReader().useLines { it.toList() }
                .map { it.trim() }
            wordLists[fileName] = elements
        }

        /** do the thing, tokenize > identify the sentence pattern > identify the closest pattern >
         reconstruct sentence **/
        val tokenArray = tokenizer(sentence.text.toString())
        val identifiedTypes = mutableListOf<String>()

        /** based from the token, we identify in what type of POS every token belong **/
        for (token in tokenArray) {
            var identifiedType: String? = null
            for ((file, wordList) in wordLists) {
                if (token in wordList) {
                    identifiedType = file
                    break
                }
            }
            if (identifiedType == null) {
                identifiedTypes.add("unknown")
            } else {
                identifiedTypes.add(identifiedType)
            }
        }

        val queryPattern = identifiedTypes.toList()
        val patternToUse = findClosestPattern(queryPattern)
        val suggested = rearrange(patternToUse, queryPattern, tokenArray)

        /** setting text view to contain the querry pattern, pattern to use, suggested sentence **/
        sentencePattern.text = queryPattern.toString()
        suggestedPattern.text = patternToUse.toString()
        suggestedSentence.text = suggested.toString()



    }

    fun tokenizer(sentence: String): List<String> {
        /** tokenize the sentence **/
        val tokens = sentence.split(" ")
        val tokenArray = mutableListOf<String>()

        for (token in tokens) {
            tokenArray.add(token)
        }

        return tokenArray
    }

    fun findClosestPattern(queryPattern: List<String>): List<String>? {
        val posPattern = listOf(
            listOf("infinitive", "noun"),
            listOf("interrogative", "verb", "noun"),
            listOf("noun", "verb", "adverb"),
            listOf("noun", "conjunction", "noun"),
            listOf("adjective", "adverb", "noun"),
            listOf("imperative", "noun", "verb"),
            listOf("adjective", "noun", "verb"),
            listOf("verb", "preposition", "noun"),
            listOf("idiomatic", "noun", "verb"),
            listOf("comparative", "adjective", "noun"),
            listOf("exclamation", "interjection"),
            listOf("verb", "preposition", "adjective"),
            listOf("noun", "grammar", "adjective")
        )

        var closestPattern: List<String>? = null
        var maxMatches = 0

        val filteredPatterns = posPattern.filter { pattern -> pattern.size == queryPattern.size }

        for (pattern in filteredPatterns) {
            var matches = 0
            val sortedQuery = queryPattern.sorted()
            val sortedPattern = pattern.sorted()
            for ((i, part) in sortedQuery.withIndex()) {
                if (part == sortedPattern[i]) {
                    matches += 1
                }
            }
            if (matches > maxMatches) {
                maxMatches = matches
                closestPattern = pattern
            }
        }

        return closestPattern
    }

    fun rearrange(patternToUse: List<String>?, queryPattern: List<String>?,
                  sentence: List<String>?): List<String?>? {

        /** Check for null values **/
        if (patternToUse == null || queryPattern == null || sentence == null) {
            println("One or more input variables are unknown. Process can't be done.")
            return null
        }

        val wordDict = queryPattern.zip(sentence).toMap()
        val rearrangedSentence = patternToUse.map { wordType -> wordDict[wordType] }.toList()
        return rearrangedSentence
    }
}