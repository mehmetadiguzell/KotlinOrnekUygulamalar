package com.devrimonder.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences : SharedPreferences
    var yasDeger : String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences=this.getSharedPreferences("com.devrimonder.sharedpreferences",
            MODE_PRIVATE)

        yasDeger=sharedPreferences.getString("yas","-1")
        if (yasDeger.equals("-1")){
            textView.text="Yaşınız : "
        }
        else{
            textView.text=  "Yaşınız : ${yasDeger}"
        }


    }
    fun save(view: View){


        val myAge=edtYas.text.toString()
        textView.text= "Yaşınız :$myAge"
        sharedPreferences.edit().putString("yas",myAge).apply()


    }
    fun delete(view: View){

        yasDeger=sharedPreferences.getString("yas","-1")
        if (yasDeger!="-1"){
            sharedPreferences.edit().remove("yas").apply()
            textView.text="Yaşınız : "
        }


    }

}