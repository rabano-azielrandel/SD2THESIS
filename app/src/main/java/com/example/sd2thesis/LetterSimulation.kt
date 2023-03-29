package com.example.sd2thesis

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.sd2thesis.PaintView.Companion.colorList
import com.example.sd2thesis.PaintView.Companion.currentBrush
import com.example.sd2thesis.PaintView.Companion.pathList
import java.io.File
import java.io.FileOutputStream
import android.content.res.AssetManager // trial
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel


class LetterSimulation : AppCompatActivity() {

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }

    private lateinit var paintView: PaintView

    private lateinit var strokeSeekBar: SeekBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter_simulation)
        supportActionBar?.hide()


        val blueButton = findViewById<ImageButton>(R.id.blueColor);
        val saveButton = findViewById<ImageButton>(R.id.save_button);
        val purpleButton = findViewById<ImageButton>(R.id.purpleColor);
        val blackButton = findViewById<ImageButton>(R.id.blackColor);
        val clearFormat = findViewById<ImageButton>(R.id.clear_button);

        paintView = findViewById(R.id.paintView)

        strokeSeekBar = findViewById(R.id.seekBar)


        val selectLetter = findViewById<ImageButton>(R.id.select_letter)
        selectLetter.setOnClickListener {
            showSingleSelectionDialog()
        }


        blueButton.setOnClickListener {
            paintBrush.setColor(Color.BLUE)
            currentColor(paintBrush.color)
        }

        saveButton.setOnClickListener {
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

            /** not yet working **/
            /**
            val classifier = ImageClassifier(assets)
            classifier.loadModel("neuralNetModel.tflite")
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val imagePath = "$downloadsDir/image.jpeg"
            val (classIndex, probVal) = classifier.predictImage(imagePath)
            val predictionMessage = "Class index: $classIndex, Probability: $probVal"
            Toast.makeText(this, predictionMessage, Toast.LENGTH_SHORT).show() **/
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

        strokeSeekBar.max = 100
        strokeSeekBar.progress = 50
        strokeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                paintBrush.strokeWidth = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not used
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not used
            }
        })

        // Set the initial stroke width of the paintBrush
        paintBrush.strokeWidth = strokeSeekBar.progress.toFloat()

    }

    private fun currentColor(color:Int){
        currentBrush = color
        path = Path()
    }

    //if the user click the Select Letter button
    private fun showSingleSelectionDialog() {
        val items = arrayOf("None", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "Ñ", "Ng", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select a letter")
        builder.setSingleChoiceItems(items, -1) { dialog, which ->
            dialog.dismiss()
            if (which == 0) {
                showImage(null, 0)
            } else {
                showDoubleSelectionDialog(which - 1)
            }
        }
        builder.create().show()
    }

    private fun showDoubleSelectionDialog(firstOptionIndex: Int) {
        val items = arrayOf("Big Letter", "Small Letter")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose:")
        builder.setSingleChoiceItems(items, -1) { dialog, which ->
            dialog.dismiss()
            showImage(firstOptionIndex, which)
        }
        builder.create().show()
    }

    private fun showImage(firstOptionIndex: Int?, secondOptionIndex: Int) {
        val imageResource = when {
            firstOptionIndex == null -> R.drawable.t_transparent
            firstOptionIndex == 0 && secondOptionIndex == 0 -> R.drawable.t_a
            firstOptionIndex == 0 && secondOptionIndex == 1 -> R.drawable.t_aa
            firstOptionIndex == 1 && secondOptionIndex == 0 -> R.drawable.t_b
            firstOptionIndex == 1 && secondOptionIndex == 1 -> R.drawable.t_bb
            firstOptionIndex == 2 && secondOptionIndex == 0 -> R.drawable.t_c
            firstOptionIndex == 2 && secondOptionIndex == 1 -> R.drawable.t_cc
            firstOptionIndex == 3 && secondOptionIndex == 0 -> R.drawable.t_d
            firstOptionIndex == 3 && secondOptionIndex == 1 -> R.drawable.t_dd
            firstOptionIndex == 4 && secondOptionIndex == 0 -> R.drawable.t_e
            firstOptionIndex == 4 && secondOptionIndex == 1 -> R.drawable.t_ee
            firstOptionIndex == 5 && secondOptionIndex == 0 -> R.drawable.t_f
            firstOptionIndex == 5 && secondOptionIndex == 1 -> R.drawable.t_ff
            firstOptionIndex == 6 && secondOptionIndex == 0 -> R.drawable.t_g
            firstOptionIndex == 6 && secondOptionIndex == 1 -> R.drawable.t_gg
            firstOptionIndex == 7 && secondOptionIndex == 0 -> R.drawable.t_h
            firstOptionIndex == 7 && secondOptionIndex == 1 -> R.drawable.t_hh
            firstOptionIndex == 8 && secondOptionIndex == 0 -> R.drawable.t_i
            firstOptionIndex == 8 && secondOptionIndex == 1 -> R.drawable.t_ii
            firstOptionIndex == 9 && secondOptionIndex == 0 -> R.drawable.t_j
            firstOptionIndex == 9 && secondOptionIndex == 1 -> R.drawable.t_jj
            firstOptionIndex == 10 && secondOptionIndex == 0 -> R.drawable.t_k
            firstOptionIndex == 10 && secondOptionIndex == 1 -> R.drawable.t_kk
            firstOptionIndex == 11 && secondOptionIndex == 0 -> R.drawable.t_l
            firstOptionIndex == 11 && secondOptionIndex == 1 -> R.drawable.t_ll
            firstOptionIndex == 12 && secondOptionIndex == 0 -> R.drawable.t_m
            firstOptionIndex == 12 && secondOptionIndex == 1 -> R.drawable.t_mm
            firstOptionIndex == 13 && secondOptionIndex == 0 -> R.drawable.t_n
            firstOptionIndex == 13 && secondOptionIndex == 1 -> R.drawable.t_nn
            firstOptionIndex == 14 && secondOptionIndex == 0 -> R.drawable.t_enye
            firstOptionIndex == 14 && secondOptionIndex == 1 -> R.drawable.t_enyeenye
            firstOptionIndex == 15 && secondOptionIndex == 0 -> R.drawable.t_ng
            firstOptionIndex == 15 && secondOptionIndex == 1 -> R.drawable.t_ngng
            firstOptionIndex == 16 && secondOptionIndex == 0 -> R.drawable.t_o
            firstOptionIndex == 16 && secondOptionIndex == 1 -> R.drawable.t_oo
            firstOptionIndex == 17 && secondOptionIndex == 0 -> R.drawable.t_p
            firstOptionIndex == 17 && secondOptionIndex == 1 -> R.drawable.t_pp
            firstOptionIndex == 18 && secondOptionIndex == 0 -> R.drawable.t_q
            firstOptionIndex == 18 && secondOptionIndex == 1 -> R.drawable.t_qq
            firstOptionIndex == 19 && secondOptionIndex == 0 -> R.drawable.t_r
            firstOptionIndex == 19 && secondOptionIndex == 1 -> R.drawable.t_rr
            firstOptionIndex == 20 && secondOptionIndex == 0 -> R.drawable.t_s
            firstOptionIndex == 20 && secondOptionIndex == 1 -> R.drawable.t_ss
            firstOptionIndex == 21 && secondOptionIndex == 0 -> R.drawable.t_t
            firstOptionIndex == 21 && secondOptionIndex == 1 -> R.drawable.t_tt
            firstOptionIndex == 22 && secondOptionIndex == 0 -> R.drawable.t_u
            firstOptionIndex == 22 && secondOptionIndex == 1 -> R.drawable.t_uu
            firstOptionIndex == 23 && secondOptionIndex == 0 -> R.drawable.t_v
            firstOptionIndex == 23 && secondOptionIndex == 1 -> R.drawable.t_vv
            firstOptionIndex == 24 && secondOptionIndex == 0 -> R.drawable.t_w
            firstOptionIndex == 24 && secondOptionIndex == 1 -> R.drawable.t_ww
            firstOptionIndex == 25 && secondOptionIndex == 0 -> R.drawable.t_x
            firstOptionIndex == 25 && secondOptionIndex == 1 -> R.drawable.t_xx
            firstOptionIndex == 26 && secondOptionIndex == 0 -> R.drawable.t_y
            firstOptionIndex == 26 && secondOptionIndex == 1 -> R.drawable.t_yy
            firstOptionIndex == 27 && secondOptionIndex == 0 -> R.drawable.t_z
            firstOptionIndex == 27 && secondOptionIndex == 1 -> R.drawable.t_zz
            else -> throw IllegalArgumentException("Invalid selection")
        }

        val imageView = findViewById<ImageView>(R.id.imageLetter)
        imageView.setImageResource(imageResource)
    }

}
class ImageClassifier(private val assets: AssetManager){
    private lateinit var interpreter: Interpreter
    private val imgWidth = 32
    private val imgHeight = 32
    private val threshold = 0.65

    fun loadModel(modelPath: String) {
        val tfliteModel = loadModelFile(modelPath)
        val options = Interpreter.Options()
        interpreter = Interpreter(tfliteModel, options)
    }

    fun predictImage(imgPath: String): Pair<Int, Float> {
        // read the image
        val imgOrig = BitmapFactory.decodeStream(assets.open(imgPath))

        // converting img to bitmap
        val img = Bitmap.createScaledBitmap(imgOrig, imgWidth, imgHeight, true)

        // sending img to preprocessing
        val input = preprocessImage(img)

        // predicting
        val output = Array(1) { FloatArray(10) }
        interpreter.run(input, output)

        val classIndex = output[0].indices.maxByOrNull { output[0][it] }!!
        val probVal = output[0][classIndex]

        if (probVal > threshold) {
            // Add label and confidence to the image
            val canvas = android.graphics.Canvas(imgOrig)
            android.graphics.Paint().apply {
                textSize = 60f
                color = android.graphics.Color.RED
            }.let {
                canvas.drawText("$classIndex : $probVal", 50f, 50f, it)
            }
        }

        return Pair(classIndex, probVal)
    }

    private fun preprocessImage(bitmap: Bitmap): ByteBuffer {
        val input = ByteBuffer.allocateDirect(imgWidth * imgHeight * 4)
        input.order(ByteOrder.nativeOrder())
        val pixels = IntArray(imgWidth * imgHeight)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        for (pixelValue in pixels) {
            val r = (pixelValue shr 16) and 0xff
            val g = (pixelValue shr 8) and 0xff
            val b = (pixelValue) and 0xff

            val gray = (r + g + b) / 3f / 255f
            input.putFloat(gray)
        }

        input.rewind()
        return input
    }

    private fun loadModelFile(modelPath: String): ByteBuffer {
        val fileDescriptor = assets.openFd("ml/$modelPath")
        val inputStream = fileDescriptor.createInputStream()
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}
