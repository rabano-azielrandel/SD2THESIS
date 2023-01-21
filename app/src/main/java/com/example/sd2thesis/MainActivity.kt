package com.example.sd2thesis

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class MainActivity : AppCompatActivity() {

    /** Fire base **/
    private var database = FirebaseDatabase.getInstance("https://sd2thesis-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private var dbRef = database.getReference("message")
    //private var storageRef = FirebaseStorage.getInstance().getReference()
    //var worksRef: StorageReference? = storageRef.child("MyWorks")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /** initializing variables **/
        val bold = findViewById<AppCompatButton>(R.id.btn_bold_format)
        val italic = findViewById<AppCompatButton>(R.id.btn_italic_format)
        val underline = findViewById<AppCompatButton>(R.id.btn_underline_format)
        val right= findViewById<AppCompatButton>(R.id.btn_right_orient)
        val center = findViewById<AppCompatButton>(R.id.btn_center_orient)
        val left = findViewById<AppCompatButton>(R.id.btn_left_orient)
        val justify = findViewById<AppCompatButton>(R.id.btn_justify_orient)
        val clearFormat = findViewById<AppCompatButton>(R.id.btn_clear_format)
        val texteditor = findViewById<EditText>(R.id.et_text_editor)
        val txteditorReturn = findViewById<AppCompatButton>(R.id.btn_txt_editor_return)
        val txteditorSave = findViewById<AppCompatButton>(R.id.btn_save)

        /** Spannable string **/
        var spannableString: SpannableString?




        /**
          *NOTE: we must highlight first the word inorder to apply the formatting.
          * bold formatting **/
        bold.setOnClickListener{
            spannableString = SpannableString(texteditor.text)
            spannableString!!.setSpan(
                StyleSpan(Typeface.BOLD),
                texteditor.selectionStart,
                texteditor.selectionEnd,0)
            texteditor.setText(spannableString)
        }

        /** italic formatting **/
        italic.setOnClickListener {
            spannableString = SpannableString(texteditor.text)
            spannableString!!.setSpan(
                StyleSpan(Typeface.ITALIC),
                texteditor.selectionStart,
                texteditor.selectionEnd,0)
            texteditor.setText(spannableString)
        }

        /** underline formatting **/
        underline.setOnClickListener {
            spannableString = SpannableString(texteditor.text)
            spannableString!!.setSpan(
                UnderlineSpan(), texteditor.selectionStart,
                texteditor.selectionEnd,0)
                texteditor.setText(spannableString)
        }

        /** right alignment **/
        right.setOnClickListener{
            texteditor.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            spannableString = SpannableString(texteditor.text)
            texteditor.setText(spannableString)
        }

        /** center alignment **/
        center.setOnClickListener{
            texteditor.textAlignment = View.TEXT_ALIGNMENT_CENTER
            spannableString = SpannableString(texteditor.text)
            texteditor.setText(spannableString)
        }

        /** left alignment **/
        left.setOnClickListener{
            texteditor.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            spannableString = SpannableString(texteditor.text)
            texteditor.setText(spannableString)
        }

        /** justify alignment **/
        justify.setOnClickListener{
            texteditor.textAlignment
        }

        /** clear formatting
         * CLEAR FORMATTING WORKS ONLY AT BOLD, ITALIC, AND UNDERLINE. **/
        clearFormat.setOnClickListener{
            spannableString = SpannableString(texteditor.text)
            val spans = spannableString!!.getSpans(
                texteditor.selectionStart,
                texteditor.selectionEnd,
                StyleSpan::class.java)

            for (styleSpan in spans) {
                spannableString!!.removeSpan(styleSpan)
            }

            texteditor.setText(spannableString)
        }

         /**  return button
            * if the user is already logged in go dashboard
            * else prompt the user to log in  **/
        txteditorReturn.setOnClickListener{
            Toast.makeText(this, "Login First", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        /** save button, not working. **/
        txteditorSave.setOnClickListener {
            Toast.makeText(this, "not available", Toast.LENGTH_LONG).show()

        }

    }



}

