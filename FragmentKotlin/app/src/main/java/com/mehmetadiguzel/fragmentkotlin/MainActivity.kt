package com.mehmetadiguzel.fragmentkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun firsFragment(view: View) {
        val fragmentManager=supportFragmentManager //fragment yöneticisi
        val fragmentTransaction=fragmentManager.beginTransaction() // işlem yapmak için oluşturlur

        val ilk_fragment=BirinciFragment()
        fragmentTransaction.replace(R.id.frameLayout,ilk_fragment).commit()



    }
    fun secondFragment(view: View) {

        val fragmentManager=supportFragmentManager //fragment yöneticisi
        val fragmentTransaction=fragmentManager.beginTransaction() // işlem yapmak için oluşturlur

        val ikinci_fragment=BirinciFragment()
        fragmentTransaction.replace(R.id.frameLayout,ikinci_fragment).commit()

        //framentIslem.add(R.id.frameLayout,ikinci_fragment).commit()
        //add -> eklemek
        //replace -> yerine eklemek
        //commit kendinden önceki işlemi sonlandırma

    }
}