package com.mehmetadiguzel.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            val notBaslikListesi=ArrayList<String>()
            val notIdListesi=ArrayList<Int>()

            val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,notBaslikListesi)
            listview.adapter=adapter


            try {
            val database=openOrCreateDatabase("Notlar", MODE_PRIVATE,null)
            val cursor=database.rawQuery("SELECT * FROM notlar",null)

            val idColumnIndex=cursor.getColumnIndex("id")
            val baslikColumnIndex=cursor.getColumnIndex("baslik")

            while (cursor.moveToNext()){

                val baslikDataBase=cursor.getString(baslikColumnIndex)
                val idGelen=cursor.getInt(idColumnIndex)

                notBaslikListesi.add(baslikDataBase)
                notIdListesi.add(idGelen)

                }
            adapter.notifyDataSetChanged()
            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }
            listview.onItemClickListener=AdapterView.OnItemClickListener { parent, view, position, id ->

                val intent=Intent(this,NotDetayActivity::class.java)
                intent.putExtra("id",notIdListesi[position])
                startActivity(intent)
            }
    }
    fun ekleGit(view: View) {
        val intent=Intent(this,NotEkle::class.java)
        startActivity(intent)
    }

}