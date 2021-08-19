package com.devrimonder.siniflarvefonksiyonlar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        sayHello("Hey","Kotlin")
        test()
        topla(10,20)
        println("Çarpımın sonucu: ${carp(4,5)}")

        //Sınıf ve Nesne
        println("-------------------------------------")
        /*
        var homer=Simpson()  //simpson sınıfından homer nesnesini oluşturma

        homer.age=25
        homer.name="Homer Simpson"
        homer.job="Artist"

        println(homer.age)

         */
        var homer=Simpson(38,"JOB","Economits")
        println(homer.meslek)

        //Boş Değerler ve Çözümleri

        println("-----------------------------")

        var myString : String?=null
        var myAge : Int? =null
        println(myAge)
        //çözüm
        //1)Null Safety
        if (myAge!=null){
            println(myAge)
        }else{
            println("myAge NULL")
        }
        //2)Safe CALL
        val result=myAge?.compareTo(2)
        println(result)
        //3 elvis

        val result2=myAge?.compareTo(2) ?: 25 //myAge? değeri belli değilse değeri 25 olur.
        println(result2)





    }
        //parametresiz değer döndermyn fonk
     //Void -Unit
    fun test(){
        println("hello kotlin")
    }
        //parametreli değer döndermeyen fonk
    fun topla(a:Int,b:Int){
        println("Toplam ${a+b} ")

    }
        //parametreli ve değer döndüren fonk
    fun carp(x : Int,y:Int):Int{
        return x*y
    }

    fun simpsonMake(view: View){


    }


    fun sayHello(isim:String,soyad:String)= println("$isim $soyad")





}