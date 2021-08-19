package com.mehmetadiguzel.handlerrunnableexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.TextureView
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    lateinit var yazdir :TextView
    var numara=0
    var runnable : Runnable= Runnable{}
    var handler = Handler(Looper.myLooper()!!)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun baslat(view: View) {
        numara=0
        runnable=object : Runnable{
            override fun run() {
                yazdir= findViewById<TextView>(R.id.textView)
                numara++
                yazdir.text="Sayaç : ${numara}"
                handler.postDelayed(runnable,1000)

            }
        }
        handler.post(runnable)

    }
    fun durdur(view: View) {
        handler.removeCallbacks(runnable)
        numara=0
        yazdir.text="Sayaç : 0"

    }
}