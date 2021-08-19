package com.mehmetadiguzel.yemektariflerisqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {//menu oluşturma

        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.yemek_ekle,menu)//menu çağırma

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {//item seçildğinde ne yapılsın

        if(item.itemId==R.id.yemek_ekle_item){
            val action=ListeFragmentDirections.actionListeFragmentToTarifFragment("menudengeldim",0)//aksiyon oluşturma
            Navigation.findNavController(this,R.id.fragment).navigate(action)//nerden nereye gideceğini belirleme
        }
        return super.onOptionsItemSelected(item)
    }

}