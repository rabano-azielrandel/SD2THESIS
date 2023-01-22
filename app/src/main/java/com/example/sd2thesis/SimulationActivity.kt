package com.example.sd2thesis

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatButton

class SimulationActivity : AppCompatActivity() {
        private lateinit var img:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        img = findViewById(R.id.imageView)
        val strButtonClicked = intent.getStringExtra("ButtonClicked")
        val simulReturn = findViewById<AppCompatButton>(R.id.btn_simul_return)
        val videoView = findViewById <View>(R.id.vv_video) as VideoView

        /** For setting path **/
        when(strButtonClicked.toString()) {

            "A" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.a
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.aa)}

            "B" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.b
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.bb)}

            "C" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.c
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.cc)}

            "D" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.d
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.dd)}

            "E" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.e
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.ee)}

            "F" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.f
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.ff)}

            "G" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.g
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.gg)}

            "H" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.h
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.hh)}

            "I" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.i
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.ii)}

            "J" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.j
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.jj)}

            "K" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.k
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.kk)}

            "L" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.l
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.ll)}

            "M" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.m
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.mm)}

            "N" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.n
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.nn)}


            "Ñ" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.enye
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.enyeenye)}

            "NG" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.ng
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.ngng)}

            "O" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.o
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.oo)}

            "P" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.p
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.pp)}

            "Q" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.q
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.qq)}

            "R" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.r
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.rr)}

            "S" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.s
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.ss)}

            "T" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.t
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.tt)}

            "U" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.u
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.uu)}

            "V" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.v
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.vv)}

            "W" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.w
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.ww)}

            "X" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.x
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.xx)}

            "Y" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.y
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.yy)}

            "Z" -> {val aPath = "android.resource://" + packageName + "/raw/" + R.raw.z
                    videoView.setVideoURI(Uri.parse(aPath))
                    img.setImageResource(R.drawable.zz)}

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