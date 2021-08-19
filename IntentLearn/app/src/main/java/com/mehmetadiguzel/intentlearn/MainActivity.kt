 package com.mehmetadiguzel.intentlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

     //Aktivitylerin yaşam döngüsü

     override fun onResume() {
         super.onResume()
         println("on resume")
     }

     override fun onPause() {
         super.onPause()
         println("on pause")
     }

     override fun onStop() {
         super.onStop()
         println("on stop")
     }

     override fun onDestroy() { //activity yok etme
         super.onDestroy()
         println("on destroy")
     }

     fun next(view: View) {
         val user=findViewById<EditText>(R.id.edtUser)
         val name=findViewById<EditText>(R.id.edtName)

         val intent=Intent(applicationContext,NextActivity::class.java) //activity geçiş
         intent.putExtra("username",user.text.toString()) //kullanıcı adını yollama
         intent.putExtra("name",name.text.toString())
         startActivity(intent) //activity başlatma
         finish() //activity geçiş yaparken ilk activity kapanır.

     }
 }