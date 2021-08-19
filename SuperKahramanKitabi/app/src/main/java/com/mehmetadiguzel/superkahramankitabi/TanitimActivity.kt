package com.mehmetadiguzel.superkahramankitabi

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tanitim.*

class TanitimActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanitim)

        val intent=intent
        val secilenKahramanIsmi=intent.getStringExtra("kahramanismi")
        textView.text=secilenKahramanIsmi

        val secilenGorsel=intent.getIntExtra("secilen",0)
        val bitmap=BitmapFactory.decodeResource(applicationContext.resources,secilenGorsel)
        imageView.setImageBitmap(bitmap)
        /*
        val secilenKahraman=SingletonClass.SecilenKahraman
        val secilenGorsel=secilenKahraman.gorsel
        imageView.setImageBitmap(secilenGorsel)

         */
    }
}