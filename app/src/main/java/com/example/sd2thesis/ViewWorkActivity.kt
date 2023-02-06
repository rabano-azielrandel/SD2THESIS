package com.example.sd2thesis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class ViewWorkActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var storageRef = FirebaseStorage.getInstance().reference
    private lateinit var user : FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_work)

        listView = findViewById(R.id.list_view)

        /** Initializing Variables **/
        val viewWorkReturn = findViewById<AppCompatButton>(R.id.btn_view_work_return)
        val list = ArrayList<String>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        listView.adapter = adapter

        /** Redirecting to dashboard **/
        viewWorkReturn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** Displaying Works of the user **/
        user = FirebaseAuth.getInstance()
        user.currentUser?.let {
            val userWorksRef = storageRef.child("users/${it.uid}/works")

            userWorksRef.listAll().addOnSuccessListener { listResult ->
                for (item in listResult.items) {
                    list.add(item.name)
                }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener {
                Log.e("Firebase", "Failed to list all files", it)
            }
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val item = adapter.getItem(position)
            item?.let {
                val fileRef = storageRef.child("users/${user.currentUser?.uid}/works/$it")
                fileRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { contents ->
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("file_name", item)
                    intent.putExtra("contents", contents)
                    intent.putExtra("overwrite_requested", true)
                    startActivity(intent)
                }
            }
        }
    }
}
