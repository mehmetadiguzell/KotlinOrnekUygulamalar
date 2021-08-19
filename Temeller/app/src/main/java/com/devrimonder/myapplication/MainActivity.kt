package com.devrimonder.myapplication

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myArray=ArrayList<String>()
        myArray.add(index = 0,element = "Remzi")
        myArray.add(index = 1,element = "Ramo")
        println(myArray[0])



        val karısık=ArrayList<Any>()
        karısık.add(true)
        karısık.add("Murat")
        karısık.add(2)


        println("Üçüncü Eleman : ${karısık[1]}")

        println("-----Setler--------")

        val mySet= setOf<Int>(1,1,2,3)
        mySet.forEach { println(it)}

        println("-----Mapler--------")

        val map1= hashMapOf<String,Int>("Ali" to 23,"Ahmet" to 28)
        println(map1["Ali"])

        val map2 = hashMapOf<String,Int>()
        map2.put("MUZ",200)
        map2.put("Armut",400)

        println(map2["Armut"])

        println("-----Switch---When----")

        var day=2
        var dayString=""

        when(day){
            1 ->dayString="Monday"
            2 ->dayString="Tuesday"
            3 ->dayString="Wednesday"
            else -> dayString=""
        }
        println(dayString)


        println("-----for döngüsü----")

        val dizi1= arrayOf(12,15,18,21,24)
        for (sayi in dizi1){
            var a=sayi/3*5
            println(a)
        }
        println("-----------------------------")
        for (i in dizi1.indices){
            var b=dizi1[i]/3*5
            println(b)
        }
        println("-----------------------------")

        val stringArray=ArrayList<String>()
        stringArray.add("Devrim")
        stringArray.add("Önder")
        for (s in stringArray){
            println(s)
        }
        stringArray.forEach { println(it) }
        println("-----------------------------")

        for (b in 0..9){
            println(b)
        }

        println("---------------While--------------")

        var j=0
        while(j<10){
            println(j)
            j++

        }













    }
}