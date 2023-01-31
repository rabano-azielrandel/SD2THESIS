package com.example.sd2thesis

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.reflect.Executable

@Suppress("DEPRECATION", "NAME_SHADOWING")
class DashboardActivity : AppCompatActivity() {

    /** For uploading docx file **/
    private val readRequestCode = 0
    private val docx : Int = 0
    private lateinit var uri : Uri
    private lateinit var storageRef : StorageReference


    /** For user **/
    private lateinit var user : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        /** INITIALIZING VARIABLE **/
        val name = findViewById<TextView>(R.id.tv_dash_name)
        val age = findViewById<TextView>(R.id.tv_dash_age)
        val grade = findViewById<TextView>(R.id.tv_dash_gradelvl)
        val org = findViewById<TextView>(R.id.tv_dash_School_Org)
        val viewProfile = findViewById<AppCompatButton>(R.id.btn_view_profile)
        val logout = findViewById<AppCompatButton>(R.id.btn_logout)
        val txtEditor = findViewById<AppCompatButton>(R.id.btn_go_txt_editor)
        val imgRecog = findViewById<AppCompatButton>(R.id.btn_go_img_recog)
        val simulPicker = findViewById<AppCompatButton>(R.id.btn_go_simul_picker)
        val dict = findViewById<AppCompatButton>(R.id.btn_go_dictionary)
        val upload = findViewById<AppCompatButton>(R.id.btn_upload_docx)
        val viewWork = findViewById<AppCompatButton>(R.id.btn_view_works)


        /** For login User **/
        user = FirebaseAuth.getInstance()

        /** Firebase Storage **/
        storageRef = FirebaseStorage.getInstance().getReference("Works")

        /** intent variable **/

        if(user.currentUser != null){
            user.currentUser?.let {
                val name = findViewById<TextView>(R.id.tv_dash_name)

                name.text = it.email
            }
        }
        var intent: Intent?


        /** View profile btn must show the personal info of the user
         * NOT WORKING**/
        viewProfile.setOnClickListener{
            Toast.makeText(this, "available next patch", Toast.LENGTH_LONG).show()
        }

        /** logout and redirect to main page
         *  NOTE: redirect palang ito walang pang logging out ng acc**/
        logout.setOnClickListener{
            user.signOut()
            Toast.makeText(this, "Successfully logged out :)", Toast.LENGTH_LONG).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        /** Text editor **/
        txtEditor.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /** Image recog **/
        imgRecog.setOnClickListener{
            intent = Intent(this, ImageRecogActivity::class.java)
            startActivity(intent)
        }

        /** Simulator Picker **/
        simulPicker.setOnClickListener{
            intent = Intent(this, SimulationPickerActivity::class.java)
            startActivity(intent)
        }

        /** Dictionary **/
        dict.setOnClickListener{
            intent = Intent(this, DictionaryActivity::class.java)
            startActivity(intent)
        }

        /** Upload **/
        upload.setOnClickListener {
            performFileSearch()

        }

        /** View Works **/
        viewWork.setOnClickListener {
            intent = Intent(this, ViewWorkActivity::class.java)
            startActivity(intent)
        }

    }

    /**For uploading**/
    private fun performFileSearch() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
        }
        startActivityForResult(intent, readRequestCode)
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == readRequestCode && resultCode == RESULT_OK) {
            resultData?.data?.also { uri ->
                contentResolver.openInputStream(uri)?.use { inputStream ->
                    val fileContent = inputStream.readBytes().toString(Charsets.UTF_8)
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("file_content", fileContent)
                    }
                    startActivity(intent)
                }
            }
        }
    }

}