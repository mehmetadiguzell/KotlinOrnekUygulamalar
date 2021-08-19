package com.mehmetadiguzel.sayacexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Abstact Class

        var yazdir=findViewById<TextView>(R.id.textView)

       object : CountDownTimer(20000,1000){
           override fun onTick(millisUntilFinished: Long) {
               yazdir.text="Kalan Süre : ${millisUntilFinished/1000}"

           }

           override fun onFinish() {
               yazdir.text="Süre Bitti"
           }

       }.start()

    }
}