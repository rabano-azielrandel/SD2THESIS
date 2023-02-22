package com.example.sd2thesis

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.opencv.android.OpenCVLoader

class ImageRecogActivity : AppCompatActivity() {

    /** camera constraints **/
    companion object{
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_recog)

        /** Initializing Variables **/
        val imgRecogReturn = findViewById<AppCompatButton>(R.id.btn_img_recog_return)
        val capture = findViewById<AppCompatButton>(R.id.btn_img_capture)


        /** Redirecting to dashboard **/
        imgRecogReturn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        /** redirecting to user's cam **/
        capture.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED) {

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{

                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE)

            }
        }
        
    }

    /** Cam constraints **/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            }else{

                Toast.makeText(this,
                    "stop it, u need help",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    /** Setting img view to show the captured img **/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val img = findViewById<ImageView>(R.id.iv_image)

        if (resultCode == Activity.RESULT_OK){
            if (resultCode == CAMERA_REQUEST_CODE){

                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
                img.setImageBitmap(thumbnail)
            }
        }
    }

    /** NOTE: WE NEED TO HAVE STORAGE FOR PHOTO TAKEN BY USERS CAM **/

}