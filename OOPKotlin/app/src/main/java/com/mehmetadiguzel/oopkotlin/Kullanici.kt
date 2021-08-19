package com.mehmetadiguzel.oopkotlin

class Kullanici:Insan {
                //abstarct sınıf
    var isim : String?=null
    var yas :Int?=null

     constructor(isim:String,yas:Int){
        this.isim=isim
        this.yas=yas
        println("Constructor çağırıldıı")
    }

    init {
        println("init çağırıldı")
    }
}