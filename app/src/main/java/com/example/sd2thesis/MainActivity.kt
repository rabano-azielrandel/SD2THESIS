package com.example.sd2thesis

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.os.Environment
import android.text.SpannableString
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class MainActivity : AppCompatActivity() {



    /** Fire base **/
    // private var database = FirebaseDatabase.getInstance("https://sd2thesis-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private var storageRef = FirebaseStorage.getInstance()
    private var worksRef: StorageReference? = storageRef.getReference("MyWorks")
    private lateinit var user : FirebaseAuth


    /**for download**/

    private lateinit var textEditor: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /** initializing variables **/
        textEditor = findViewById(R.id.et_text_editor)
        val bold = findViewById<AppCompatButton>(R.id.btn_bold_format)
        val italic = findViewById<AppCompatButton>(R.id.btn_italic_format)
        val underline = findViewById<AppCompatButton>(R.id.btn_underline_format)
        val right= findViewById<AppCompatButton>(R.id.btn_right_orient)
        val center = findViewById<AppCompatButton>(R.id.btn_center_orient)
        val left = findViewById<AppCompatButton>(R.id.btn_left_orient)
        val justify = findViewById<AppCompatButton>(R.id.btn_justify_orient)
        val clearFormat = findViewById<AppCompatButton>(R.id.btn_clear_format)
        val txteditorReturn = findViewById<AppCompatButton>(R.id.btn_txt_editor_return)
        val txteditorSave = findViewById<AppCompatButton>(R.id.btn_save)
        val txtDownload = findViewById<AppCompatButton>(R.id.btn_download)

        /** Spannable string **/
        var spannableString: SpannableString?




        /**
          *NOTE: we must highlight first the word inorder to apply the formatting.
          * bold formatting **/
        bold.setOnClickListener{
            spannableString = SpannableString(textEditor.text)
            spannableString!!.setSpan(
                StyleSpan(Typeface.BOLD),
                textEditor.selectionStart,
                textEditor.selectionEnd,0)
            textEditor.setText(spannableString)
        }

        /** italic formatting **/
        italic.setOnClickListener {
            spannableString = SpannableString(textEditor.text)
            spannableString!!.setSpan(
                StyleSpan(Typeface.ITALIC),
                textEditor.selectionStart,
                textEditor.selectionEnd,0)
            textEditor.setText(spannableString)
        }

        /** underline formatting **/
        underline.setOnClickListener {
            spannableString = SpannableString(textEditor.text)
            spannableString!!.setSpan(
                UnderlineSpan(), textEditor.selectionStart,
                textEditor.selectionEnd,0)
                textEditor.setText(spannableString)
        }

        /** right alignment **/
        right.setOnClickListener{
            textEditor.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            spannableString = SpannableString(textEditor.text)
            textEditor.setText(spannableString)
        }

        /** center alignment **/
        center.setOnClickListener{
            textEditor.textAlignment = View.TEXT_ALIGNMENT_CENTER
            spannableString = SpannableString(textEditor.text)
            textEditor.setText(spannableString)
        }

        /** left alignment **/
        left.setOnClickListener{
            textEditor.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            spannableString = SpannableString(textEditor.text)
            textEditor.setText(spannableString)
        }

        /** justify alignment **/
        justify.setOnClickListener{
            textEditor.textAlignment = View.TEXT_ALIGNMENT_GRAVITY
        }

        /** clear formatting
         * CLEAR FORMATTING WORKS ONLY AT BOLD, ITALIC, AND UNDERLINE. **/
        clearFormat.setOnClickListener{
            spannableString = SpannableString(textEditor.text)
            val spans = spannableString!!.getSpans(
                textEditor.selectionStart,
                textEditor.selectionEnd,
                StyleSpan::class.java)

            for (styleSpan in spans) {
                spannableString!!.removeSpan(styleSpan)
            }

            textEditor.setText(spannableString)
        }

         /**  return button
            * if the user is already logged in go dashboard
            * else prompt the user to log in
            * not yet working **/
        txteditorReturn.setOnClickListener{

            user = FirebaseAuth.getInstance()

            if(user.currentUser != null){
                user.currentUser?.let {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Login First", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }



        /** save button **/
        txteditorSave.setOnClickListener {
            val data = textEditor.text.toString()

            user = FirebaseAuth.getInstance()

            if(user.currentUser != null){
                user.currentUser?.let {
                    /** NOTE: THE USER CANT SET FILE NAME**/
                    /** NOTE: THE SYSTEM STILL DOESN'T KNOW WHO IS THE OWNER OF THE FILE **/
                    try {
                        /** Conversion of text into bytes **/
                        worksRef!!.child(data).putBytes(data.toByteArray()).addOnSuccessListener {
                            Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_LONG).show()
                        }
                    } catch (e : Exception){
                        Toast.makeText(this, "Failed to Upload", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Login First", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

        }


        /**FOR UPLOAD**/
        val fileContent = intent.getStringExtra("file_content")
        if (fileContent != null) {
            textEditor.setText(fileContent)
        }

        /**download button**/
        txtDownload.setOnClickListener {
            showFileNameInputDialog()
        }

    }

    /**for download**/
    private fun showFileNameInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter file name")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ ->
            val fileName = input.text.toString()
            if (fileName.isNotEmpty()) {
                saveFile(fileName)
            } else {
                Toast.makeText(this, "File name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun saveFile(fileName: String) {

            val fileText = textEditor.text.toString()
            val file =
                File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "$fileName.txt")

            if (!file.exists()) {
                file.writeText(fileText)
                Toast.makeText(this,
                    "Your file is downloaded successfully! Location: Downloads",
                    Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "File already exists with that name", Toast.LENGTH_SHORT)
                    .show()
            }
        }


}

