package com.example.sd2thesis

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageButton
import android.widget.Toast
import com.example.sd2thesis.PaintView.Companion.colorList
import com.example.sd2thesis.PaintView.Companion.currentBrush
import com.example.sd2thesis.PaintView.Companion.pathList
import java.io.File
import java.io.FileOutputStream

class LetterSimulation : AppCompatActivity() {

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }

    private lateinit var paintView: PaintView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_simulation)
        supportActionBar?.hide()

        val redButton = findViewById<ImageButton>(R.id.redColor);
        val blueButton = findViewById<ImageButton>(R.id.blueColor);
        val purpleButton = findViewById<ImageButton>(R.id.purpleColor);
        val blackButton = findViewById<ImageButton>(R.id.blackColor);
        val clearFormat = findViewById<ImageButton>(R.id.whiteColor);

        paintView = findViewById(R.id.paintView)

        redButton.setOnClickListener {
            paintBrush.setColor(Color.RED)
            currentColor(paintBrush.color)
        }

        blueButton.setOnClickListener {
            val bitmap = Bitmap.createBitmap(paintView.width, paintView.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            paintView.draw(canvas)

            // Save the bitmap to the device's Downloads folder
            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(folder, "my_image.png")
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            Toast.makeText(this, "Image saved to downloads", Toast.LENGTH_SHORT).show()
        }

        purpleButton.setOnClickListener {
            paintBrush.setColor(Color.MAGENTA)
            currentColor(paintBrush.color)
        }

        blackButton.setOnClickListener {
            paintBrush.setColor(Color.BLACK)
            currentColor(paintBrush.color)
        }

        clearFormat.setOnClickListener {
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    private fun currentColor(color:Int){
        currentBrush = color
        path = Path()
    }
}