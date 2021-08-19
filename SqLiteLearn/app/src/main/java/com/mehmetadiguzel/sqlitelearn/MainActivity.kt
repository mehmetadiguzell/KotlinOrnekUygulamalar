package com.mehmetadiguzel.sqlitelearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val veritabani=openOrCreateDatabase("Urunler", MODE_PRIVATE,null) //veritabanı oluşturma

            //önceden yoksa urunler tablosunu oluştur.
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS urunler(id INTEGER PRIMARY KEY,isim VARCHAR,fiyat INT)")

            //veritabanına veri kaydetme
            //veritabani.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Ayakkabi',100)")
            //veritabani.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Atkı',10)")
            //veritabani.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Elbise',35)")
            //veritabani.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Tişort',40)")
            //veritabani.execSQL("INSERT INTO urunler(isim,fiyat) VALUES('Bere',50)")


            //veritabani.execSQL("DELETE FROM urunler WHERE id=5") //veri silme
            //veritabani.execSQL("UPDATE urunler SET fiyat=25 WHERE id=2") //güncelleme
            veritabani.execSQL("UPDATE urunler SET isim='Ayyakab' WHERE id=1") //güncelleme


            //FİLTRELEME

            //val cursor=veritabani.rawQuery("SELECT * FROM urunler WHERE isim LIKE '%e'",null)//sonu e olanları getir
            //val cursor=veritabani.rawQuery("SELECT * FROM urunler WHERE isim LIKE 'A%'",null)//BAŞI A olanları getir
            //val cursor=veritabani.rawQuery("SELECT * FROM urunler WHERE id=2",null)
            //val cursor=veritabani.rawQuery("SELECT * FROM urunler WHERE isim='Bere'",null)
            val cursor=veritabani.rawQuery("SELECT * FROM urunler",null)
            //imleç,veriler üzerinde gezinmeyi sağlar




            //VERİLERİ ÇEKME
            val idColumnIndex=cursor.getColumnIndex("id")
            val isimColumnIndex=cursor.getColumnIndex("isim")
            val fiyatColumnIndex=cursor.getColumnIndex("fiyat")

            while (cursor.moveToNext()){
                println("İD : ${cursor.getInt(idColumnIndex)}")
                println("İSİM : ${cursor.getString(isimColumnIndex)}")
                println("FİYAT: ${cursor.getInt(fiyatColumnIndex)}")
            }
            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()

        }
    }
}