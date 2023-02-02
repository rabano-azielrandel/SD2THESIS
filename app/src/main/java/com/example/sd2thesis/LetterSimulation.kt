package com.example.sd2thesis

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.sd2thesis.PaintView.Companion.colorList
import com.example.sd2thesis.PaintView.Companion.currentBrush
import com.example.sd2thesis.PaintView.Companion.pathList

class LetterSimulation : AppCompatActivity() {

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_simulation)
        supportActionBar?.hide()

        val redButton = findViewById<ImageButton>(R.id.redColor);
        val blueButton = findViewById<ImageButton>(R.id.blueColor);
        val purpleButton = findViewById<ImageButton>(R.id.purpleColor);
        val blackButton = findViewById<ImageButton>(R.id.blackColor);
        val clearFormat = findViewById<ImageButton>(R.id.whiteColor);

        redButton.setOnClickListener {
            paintBrush.setColor(Color.RED)
            currentColor(paintBrush.color)
        }

        blueButton.setOnClickListener {
            paintBrush.setColor(Color.BLUE)
            currentColor(paintBrush.color)
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