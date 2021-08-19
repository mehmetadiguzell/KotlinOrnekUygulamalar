package com.mehmetadiguzel.firebaseexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class KullaniciActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=FirebaseAuth.getInstance()

        val otoGiris=auth.currentUser
        if(otoGiris !=null){
            val intent =Intent(this,HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun girisYap(view: View) {
        auth.signInWithEmailAndPassword(emailText.text.toString(),pasText.text.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                val guncelKullanici=auth.currentUser?.email.toString()
                Toast.makeText(this,"Hoşgeldin ${guncelKullanici}",Toast.LENGTH_LONG).show()

                val intent=Intent(this,HaberlerActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }
    fun kaydol(view: View) {
        val email=emailText.text.toString()
        val sifre=pasText.text.toString()

        //kullanıcı oluşturma
        auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener {
            //asenkron
            if(it.isSuccessful){
                //başarılysa diğer aktiviteye git
                val intent=Intent(this,HaberlerActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            //hata olursa kullanıcıya göster
            Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }

}