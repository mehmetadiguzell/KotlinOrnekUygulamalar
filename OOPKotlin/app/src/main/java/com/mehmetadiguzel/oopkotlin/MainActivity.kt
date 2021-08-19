package com.mehmetadiguzel.oopkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("--------Sınıflar-----")
        val kullanici =Kullanici("Ahmet",50)//nesne oluşturma
        val digerKullanici=Kullanici("Murat",70)



        println("--------Encapsulation-----")

        var ahmet=Sanatci("Ahmet",50,"Müzisyen")
        println(ahmet.isim)

        println("--------Inheritance-----")
        val mehmet=OzelSanatci("Mehmet",25,"Tiyatrocu")
        mehmet.sarkiSoyle()

        println("--------Polymorphism-----")

        //Statik Polymorphism
        val islem=Islemler()
        println(islem.carpma())
        println(islem.carpma(2,2))
        println(islem.carpma(4,5,6))

        //Dinamik Polymorphism
        val kedi=Hayvan()
        kedi.sesCikar()

        val barley=Kopek()
        barley.sesCikar()
        barley.kopekFonsiyon()

        println("--------Abstraction & Interface-------")
        kullanici.insanFonksiyonu()//abstrac sınıf

        //interface
        var gitar = Gitar()
        gitar.marka="Gitar Marka"
        gitar.elektro=true
        gitar.bilgi()
        println(gitar.oda)

        println("--------Lambda Gösterimi-------")
        yazdir("ahmet")

        val yazdirLambda={gelenString : String -> println(gelenString)}
        yazdirLambda("test lambda")

        val carpmaLambda={a:Int,b:Int -> a*b}
        println(carpmaLambda(6,7))

        val carpmaLambdaV2 :(Int,Int) -> Int={a,b -> a*b}
        println(carpmaLambdaV2(6,7))


    }

    fun yazdir(string: String){
        println(string)
    }
}