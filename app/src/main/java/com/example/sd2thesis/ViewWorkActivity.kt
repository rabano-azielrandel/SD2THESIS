package com.example.sd2thesis

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.storage.FirebaseStorage


class ViewWorkActivity : AppCompatActivity() {

    private val storageRef = FirebaseStorage.getInstance().getReference("MyWorks")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_work)

        /** Initializing Variables **/
        val viewWorkReturn = findViewById<AppCompatButton>(R.id.btn_view_work_return)
        val download = findViewById<AppCompatButton>(R.id.btn_download)
        val refresh = findViewById<AppCompatButton>(R.id.btn_refresh)
        val workList = findViewById<ListView>(R.id.lv_worklist)
        val list = ArrayList<String>()
        val adapter = ArrayAdapter(this, android.R.layout.list_content, list)


        /** Redirecting to dashboard **/
        viewWorkReturn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** Displaying Works of the user **/

        workList.adapter = adapter




    }
}



