package com.mehmetadiguzel.oopkotlin

open class Sanatci(isim:String, yas:Int, meslek:String) {
    var isim :String?=isim
        private set  //değeri değiştirilemez  değerine erişilebilir
        get         //değerine erişilebilir

    private var yas:Int?=yas

    private var meslek:String?=meslek
}