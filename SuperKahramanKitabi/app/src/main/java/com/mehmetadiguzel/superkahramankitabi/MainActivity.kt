package com.mehmetadiguzel.superkahramankitabi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tanitim.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var superKahramanİsimleri=ArrayList<String>()
        superKahramanİsimleri.add("edtBaşlık.text.toString()")
        superKahramanİsimleri.add("Batman")
        superKahramanİsimleri.add("Superman")
        superKahramanİsimleri.add("Iron Man")
        superKahramanİsimleri.add("Spider Man")

        //Verimsiz Gösterim
        //Bir görsel kullanmak için,drawblediki resimi alıp bitmape çevirmek için kullanılır.
        /*

        var batmanBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.batman)
        var supermanBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.superman)
        var ironmanBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ironman)
        var spidermanBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.spiderman)

        var superKahramanGorselleri=ArrayList<Bitmap>()
        superKahramanGorselleri.add(batmanBitmap)
        superKahramanGorselleri.add(supermanBitmap)
        superKahramanGorselleri.add(ironmanBitmap)
        superKahramanGorselleri.add(spidermanBitmap)

         */
        //Verimli Tanımlama
        val batmanDrawbleId=R.drawable.batman
        val supermanDrawbleId=R.drawable.superman
        val ironmanDrawbleId=R.drawable.ironman
        val spidermanDrawbleId=R.drawable.spiderman

        val superKahramanDrawbleListesi=ArrayList<Int>()
        superKahramanDrawbleListesi.add(batmanDrawbleId)
        superKahramanDrawbleListesi.add(supermanDrawbleId)
        superKahramanDrawbleListesi.add(ironmanDrawbleId)
        superKahramanDrawbleListesi.add(spidermanDrawbleId)
        //Adapter
        val layoutmanager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutmanager

        val adapter=RecyclearAdapter(superKahramanİsimleri,superKahramanDrawbleListesi)
        recyclerView.adapter=adapter


    }
}