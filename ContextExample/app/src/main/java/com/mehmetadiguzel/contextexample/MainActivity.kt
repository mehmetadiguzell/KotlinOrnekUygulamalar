package com.mehmetadiguzel.contextexample

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toast , Aler -> Mesaj GÖSTERME YÖNTEMLERİ
        //applicationContext -> app context
        //this ,this@MainActivity -> aktivitenin contexti
        Toast.makeText(this,"Hoşgeldiniz ! ",Toast.LENGTH_LONG).show()
    }

    fun mesajGoster(view: View) {
        //AlertDialog kullanımı

        val uyariMesaji=AlertDialog.Builder(this)
        uyariMesaji.setTitle("ŞİFRE HATASI")

        uyariMesaji.setMessage("Yeniden Denemek İster Misiniz ?")

        uyariMesaji.setPositiveButton("Evet",DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(this,"Yeniden Deneniyor",Toast.LENGTH_SHORT).show()
        })
        //Lambda Gösterimi

        uyariMesaji.setNegativeButton("Hayır",DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(this,"Hayıra Bastınız",Toast.LENGTH_SHORT).show()
        })
        uyariMesaji.show() //Uyarı Gösteriliyor




    }
}