package com.example.sd2thesis

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatButton

class SimulationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        val strButtonClicked = intent.getStringExtra("ButtonClicked")
        val simulReturn = findViewById<AppCompatButton>(R.id.btn_simul_return)
        val videoView = findViewById <View>(R.id.vv_video) as VideoView

        /** For setting path **/
        when(strButtonClicked.toString()) {

            "A" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.a
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "B" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.b
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "C" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.c
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "D" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.d
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "E" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.e
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "F" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.f
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "G" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.g
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "H" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.h
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "I" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.i
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "J" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.j
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "K" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.k
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "L" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.l
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "M" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.m
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "N" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.n
                    videoView.setVideoURI(Uri.parse(aPath)) }


            "Ñ" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.enye
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "Ng" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.ng
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "O" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.o
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "P" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.p
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "Q" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.q
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "R" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.r
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "S" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.s
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "T" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.t
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "U" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.u
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "V" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.v
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "W" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.w
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "X" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.x
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "Y" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.y
                    videoView.setVideoURI(Uri.parse(aPath)) }

            "Z" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.z
                    videoView.setVideoURI(Uri.parse(aPath)) }

        }

        /** Playing the vid base on the set path **/
        videoView.setMediaController(MediaController( this))
        videoView.start()

        /** Redirecting simulation picker **/
        simulReturn.setOnClickListener {
            val intent = Intent(this, SimulationPickerActivity::class.java)
            startActivity(intent)
        }

    }
}