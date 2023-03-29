package com.example.sd2thesis

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatButton

class TutorialSimulation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_simulation)

        val img_letter = findViewById<View>(R.id.t_letters) as ImageView
        val vid_letter = findViewById <View>(R.id.t_videos) as VideoView
        val a = findViewById<AppCompatButton>(R.id.btn_ta)
        val b = findViewById<AppCompatButton>(R.id.btn_tb)
        val c = findViewById<AppCompatButton>(R.id.btn_tc)
        val d = findViewById<AppCompatButton>(R.id.btn_td)
        val e = findViewById<AppCompatButton>(R.id.btn_te)
        val f = findViewById<AppCompatButton>(R.id.btn_tf)
        val g = findViewById<AppCompatButton>(R.id.btn_tg)
        val h = findViewById<AppCompatButton>(R.id.btn_th)
        val i = findViewById<AppCompatButton>(R.id.btn_ti)
        val j = findViewById<AppCompatButton>(R.id.btn_tj)
        val k = findViewById<AppCompatButton>(R.id.btn_tk)
        val l = findViewById<AppCompatButton>(R.id.btn_tl)
        val m = findViewById<AppCompatButton>(R.id.btn_tm)
        val n = findViewById<AppCompatButton>(R.id.btn_tn)
        val Ñ = findViewById<AppCompatButton>(R.id.btn_tenye)
        val ng = findViewById<AppCompatButton>(R.id.btn_tng)



        /** A **/
        a.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.a
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.aa)
        }

        /** B **/
        b.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.b
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.bb)
        }

        /** C **/
        c.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.c
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.cc)
        }

        /** D **/
        d.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.d
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.dd)
        }

        /** E **/
        e.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.e
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.ee)
        }

        /** F **/
        f.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.f
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.ff)
        }

        /** G **/
        g.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.g
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.gg)
        }
        /** H **/
        h.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.h
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.hh)
        }
        /** I **/
        i.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.i
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.ii)
        }
        /** J **/
        j.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.j
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.jj)
        }
        /** K **/
        k.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.k
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.kk)
        }
        /** L **/
        l.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.l
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.ll)
        }
        /** M **/
        m.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.m
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.mm)
        }
        /** N **/
        n.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.n
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.nn)
        }
        /** Ñ **/
        Ñ.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.enye
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.enyeenye)
        }
        /** Ng **/
        ng.setOnClickListener {
            val aPath = "android.resource://" + packageName + "/raw/" + R.raw.ng
            vid_letter.setVideoURI(Uri.parse(aPath))
            img_letter.setImageResource(R.drawable.ngng)
        }




        /** Playing the vid base on the set path **/
        vid_letter.setMediaController(MediaController( this))
        vid_letter.start()
    }
}