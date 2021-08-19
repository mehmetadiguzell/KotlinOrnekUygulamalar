package com.mehmetadiguzel.oopkotlin

class Kopek: Hayvan() {
    fun kopekFonsiyon(){
        super.sesCikar()//super kalıtım aldığı sınıfı refanrs verir.

    }
    override fun sesCikar(){
        println("Köpek Sınıfı")

    }

}