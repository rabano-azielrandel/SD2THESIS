package com.example.sd2thesis

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.util.Log
import com.google.firebase.storage.StorageReference
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.storage.FirebaseStorage

class ViewWorkActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var storageRef: StorageReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_work)

        listView = findViewById(R.id.list_view)
        storageRef = FirebaseStorage.getInstance().getReference("MyWorks")

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

        storageRef.listAll().addOnSuccessListener { listResult ->
            for (item in listResult.items) {
                list.add(item.name)
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener {
            Log.e("Firebase", "Failed to list all files", it)
        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val item = adapter.getItem(position)
            val fileRef = item?.let { storageRef.child(it) }
            fileRef?.getBytes(Long.MAX_VALUE)?.addOnSuccessListener { contents ->
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("contents", contents)
                startActivity(intent)
            }
        }








    }
}





