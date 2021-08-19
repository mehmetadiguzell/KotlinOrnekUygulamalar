package com.mehmetadiguzel.kotlinmessenger

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button_register.setOnClickListener {
            kullaniciKayit()

        }


        giris_ekrani_textview.setOnClickListener {
            //giriş aktivitiysine git
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        btn_resim_sec.setOnClickListener {
            //galeriden resim seçme
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)
        }

    }
    var resimUri:Uri?=null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==0 && resultCode==Activity.RESULT_OK && data !=null){
            //fotoğraf seçildiğinde yapılacaklar
               resimUri.let {
                  resimUri=data.data   //uri verinin depolandığı adresi temsil eder
               }

            val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,resimUri)

            //resimi arkplana atma
            val bitmapDrawable=BitmapDrawable(bitmap)
            btn_resim_sec.setBackgroundDrawable(bitmapDrawable)

        }
    }

    private fun kullaniciKayit(){
        val email=email_edittext_register.text.toString()
        val pass=pass_edittext_register.text.toString()
        if (email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this,"Lüften E-posta ve Şifre giriniz",Toast.LENGTH_LONG).show()
        }

        Log.d("MainActivity","Email : $email")
        Log.d("MainActivity","Pass : $pass")
        //firebase ile kimlik doğrulama autherication
        //kullanıcı kaydetme
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                //eğer başarılı ise
                firebaseResimEkle()
            }
            .addOnFailureListener {
                //eğer kayıt başarısız olursa
                //örneğin geçersiz e posta girilirse
                Toast.makeText(this,"Lüften Geçerli E-posta giriniz",Toast.LENGTH_LONG).show()
            }
    }
    private fun firebaseResimEkle(){
        if (resimUri==null) return
        val filename=UUID.randomUUID().toString()
        val ref=FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(resimUri!!)
            .addOnSuccessListener {

                ref.downloadUrl.addOnSuccessListener {
                    firebaseKaydet(it.toString())
                }
            }
            .addOnFailureListener {
                
            }
    }
    private fun firebaseKaydet(profileImgUrl:String){
        val uid=FirebaseAuth.getInstance().uid ?:""
        val ref=FirebaseFirestore.getInstance().collection("/images/$uid")

        val user=User(uid,username_edittext_register.text.toString(),profileImgUrl )
        ref.add(user).addOnSuccessListener {
        Log.d("RegisterActivity","Kullanıcı eklendi")
            
        }
    }
}
class User(val uid: String,val username:String,val profileImgUrl:String )