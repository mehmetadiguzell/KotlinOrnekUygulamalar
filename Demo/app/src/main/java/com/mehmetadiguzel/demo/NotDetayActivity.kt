package com.mehmetadiguzel.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_not_detay.*

class NotDetayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_detay)

        val intent=intent
        val gelen_id=intent.getIntExtra("id",0).toString()


        try{
            val dataBase=openOrCreateDatabase("Notlar", MODE_PRIVATE,null)
            val cursor=dataBase.rawQuery("SELECT * FROM notlar WHERE id=${gelen_id}",null)


            val baslikColumnIndex=cursor.getColumnIndex("baslik")
            val notColumnIndex=cursor.getColumnIndex("note")

            while (cursor.moveToNext()){
                //tıklanan listview göre başlığı ve notu alma
                println("BAŞLIK:${cursor.getString(baslikColumnIndex)}")
                println("Not:${cursor.getString(notColumnIndex)}")
                textView.text="Not:${cursor.getString(notColumnIndex)}"
            }
            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}