package com.mehmetadiguzel.firebaseexample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_fotograf_paylasma.*
import java.sql.Timestamp
import java.util.*
import java.util.jar.Manifest

class FotografPaylasma : AppCompatActivity() {
    var secilenGorsel : Uri?=null
    var secilenBitmap :Bitmap?=null
    private lateinit var stroge:FirebaseStorage
    private lateinit var auth: FirebaseAuth
    private lateinit var databese :FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotograf_paylasma)
        stroge= FirebaseStorage.getInstance()
        auth= FirebaseAuth.getInstance()
        databese= FirebaseFirestore.getInstance()
    }

    fun gorselSec(view: View) {
        //izin verilmemişse izin iste
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else{
            //izin verilmişse
            val galeriIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntent,2)
        }

    }
    fun paylas(view: View) {
        //depo işemleri
        //UUID -> UNİVERSAL İNUQE ID
        val uuid=UUID.randomUUID() //rasgale id oluştur
        val gorselIsmi="${uuid}.jpg"
        val referans=stroge.reference
        val gorselReferans=referans.child("images").child(gorselIsmi)
        if(secilenGorsel!=null){
            gorselReferans.putFile(secilenGorsel!!).addOnSuccessListener {
            val yuklenenGorselReferans=FirebaseStorage.getInstance().reference.child("images").child(gorselIsmi)
                yuklenenGorselReferans.downloadUrl.addOnSuccessListener { uri ->
                    val downloadUrl=uri.toString()
                    val guncelKullaniciEmail=auth.currentUser!!.email.toString()
                    val kullaniciYorumu=yorumText.text.toString()
                    val tarih=com.google.firebase.Timestamp.now()

                    //veritabanı işlemleri
                    val postHashMap= hashMapOf<String,Any>()
                    postHashMap.put("dowloandurl",downloadUrl)
                    postHashMap.put("kullaniciemail",guncelKullaniciEmail)
                    postHashMap.put("kullaniciyorum",kullaniciYorumu)
                    postHashMap["tarih"] = tarih

                    //onCompelte ->işlem bittiğinde
                    //isSuccesfull -> işlem başarı olursa
                    //addOnFailureListener ->bir hata olursa
                    databese.collection("Post").add(postHashMap).addOnCompleteListener {
                        if(it.isSuccessful){
                                finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }


                }
            }.removeOnFailureListener {
                Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }
        //bir sonuç dönmüşse
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==1){
            if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //izin verilmişse yapılacaklar
                val galeriIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
        //dönmüş sonuç
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if(requestCode==2 && resultCode==Activity.RESULT_OK && data!=null){
                secilenGorsel=data.data
                if(secilenGorsel!=null){

                    if (Build.VERSION.SDK_INT>=28){
                        val source=ImageDecoder.createSource(this.contentResolver,secilenGorsel!!)
                        secilenBitmap=ImageDecoder.decodeBitmap(source)
                        imageView.setImageBitmap(secilenBitmap)

                    }else{
                        secilenBitmap=MediaStore.Images.Media.getBitmap(this.contentResolver,secilenGorsel)
                        imageView.setImageBitmap(secilenBitmap)
                    }
                }
            }

        super.onActivityResult(requestCode, resultCode, data)
    }
}