package com.example.sd2thesis

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class DashboardActivity : AppCompatActivity() {

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

        /** intent variable **/
        var intent: Intent?

        /** THIS SHOULD SHOW ACCORDING TO USERS INFO**/
        "${name.text} Not available right now".also { name.text = it }
        "${age.text} Not available right now".also { age.text = it }
        "${grade.text} Not available right now".also { grade.text = it }
        "${org.text} Not available right now".also { org.text = it }

        /** View profile btn must show the personal info of the user
         * NOT WORKING**/
        viewProfile.setOnClickListener{
            Toast.makeText(this, "available next patch", Toast.LENGTH_LONG).show()
        }

        /** logout and redirect to main page
         *  NOTE: redirect palang ito walang pang logging out ng acc**/
        logout.setOnClickListener{
            Toast.makeText(this, "Successfully logged out :)", Toast.LENGTH_LONG).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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

        /** Upload is not working yet**/
        upload.setOnClickListener {
            Toast.makeText(this, "available next patch", Toast.LENGTH_LONG).show()
        }

        /** View Works **/
        viewWork.setOnClickListener {
            intent = Intent(this, ViewWorkActivity::class.java)
            startActivity(intent)
        }

    }
}