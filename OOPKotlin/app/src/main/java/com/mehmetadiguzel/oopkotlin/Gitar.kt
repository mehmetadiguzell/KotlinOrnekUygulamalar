package com.mehmetadiguzel.oopkotlin

class Gitar :Enstruman,Dekorasyon {
    var marka :String?=null
    var elektro:Boolean?=null
    override var oda: String
        get() = "Salon"      //oda değerini atamak
        set(value) {}
}