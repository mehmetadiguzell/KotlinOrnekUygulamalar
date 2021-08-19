package com.mehmetadiguzel.demo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_not_ekle.*
import java.lang.Exception

class NotEkle : AppCompatActivity() {



   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_ekle)

    }

    fun notEkle(view: View) {

        try {
            val database= openOrCreateDatabase("Notlar", MODE_PRIVATE,null)

            database.execSQL("CREATE TABLE IF NOT EXISTS notlar(id INTEGER PRIMARY KEY,baslik VARCHAR,note VARCHAR)")

            val gelen_baslik=et_baslik.text.toString()
            val gelen_not=et_not.text.toString()

            if (gelen_baslik.isEmpty() && gelen_not.isEmpty()){
            Toast.makeText(applicationContext,"Not ve Başlık Eklemeyi Unuttunuz.",Toast.LENGTH_LONG).show()
            }
            if(gelen_baslik.isEmpty() ){
                Toast.makeText(applicationContext,"Lütfen Başlık Ekleyin",Toast.LENGTH_LONG).show()
            }else if(gelen_not.isEmpty()){
                Toast.makeText(applicationContext,"Lütfen Not Ekleyin",Toast.LENGTH_LONG).show()
            }else{
                database.execSQL("INSERT INTO notlar(baslik,note) VALUES ('${gelen_baslik}','${gelen_not}')")
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }catch (e:Exception){
            e.printStackTrace()
        }

    }

}