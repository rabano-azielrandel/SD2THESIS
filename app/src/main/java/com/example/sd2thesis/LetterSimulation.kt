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

            // Create a new bitmap with white background
            val newBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
            val newCanvas = Canvas(newBitmap)
            newCanvas.drawColor(Color.WHITE)

            // Draw the original bitmap on top of the white background
            newCanvas.drawBitmap(bitmap, 0f, 0f, null)

            // Save the new bitmap to the device's Downloads folder
            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(folder, "image.jpeg")

            if (file.exists()) {
                file.delete()
            }

            val outputStream = FileOutputStream(file)
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
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