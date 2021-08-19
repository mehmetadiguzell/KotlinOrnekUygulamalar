package com.mehmetadiguzel.kotlinmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email=email_edittext_register.text.toString()
            val pass=pass_edittext_register.text.toString()
            if (email.isEmpty() || pass.isEmpty()){
                Toast.makeText(this,"Lüften E-posta ve Şifre giriniz", Toast.LENGTH_LONG).show()
            }
            //giriş doğrulama
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)

        }

    }
}