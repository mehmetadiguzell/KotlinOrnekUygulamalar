package com.mehmetadiguzel.intentlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        val kullanici=findViewById<TextView>(R.id.textUser)
        val ad=findViewById<TextView>(R.id.textName)

        val intent=intent
        val username=intent.getStringExtra("username") // yollanılan intenti alma
        val name= intent.getStringExtra("name")

        kullanici.text="Username :" + username
        ad.text="Name :" + name
    }
}