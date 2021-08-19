package com.mehmetadiguzel.yemektariflerisqlite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_tarif.*
import java.io.ByteArrayOutputStream
import java.util.jar.Manifest


class TarifFragment : Fragment() {

    var secilenGorsel : Uri?=null
    var secilenBitmap : Bitmap?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            kaydet(it)
            //SQLite Kaydetme
            val yemekIsmi=yemekismiText.text.toString()
            val yemekMalzeme=yemekmalzameText.text.toString()

            if(secilenBitmap!=null){
                val kucukBitmap=kucukBitmap(secilenBitmap!!,300)
                val outputStream=ByteArrayOutputStream()//görseli veriye dönüştürme
                kucukBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
                //sıkıştırma
                val byteDizi=outputStream.toByteArray()

                try {
                    context?.let {
                        val database=it.openOrCreateDatabase("Yemekler",Context.MODE_PRIVATE,null)
                        database.execSQL("CREATE TABLE IF NOT EXISTS yemekler(id INTEGER PRIMARY KEY,yemekismi VARCHAR,yemekmalzemesi VARCHAR,gorsel BLOB)")
                    val sqlString="INSERT INTO yemekler(yemekismi,yemekmalzemesi,gorsel) VALUES(?,?,?)"
                        //statment-> string olan sql sorgusu çalıştırıp,index şeklinde kaydetmeye yarar.
                        val statement=database.compileStatement(sqlString)
                        statement.bindString(1,yemekIsmi)
                        statement.bindString(2,yemekMalzeme)
                        statement.bindBlob(3,byteDizi)
                        statement.execute()
                    }
                }catch (e:Exception){
                    e.printStackTrace()

                }
                val action=TarifFragmentDirections.actionTarifFragmentToListeFragment()
                Navigation.findNavController(view).navigate(action)

            }


        }
        imageView1.setOnClickListener {
            gorselSec(it)
        }
        arguments?.let {
            var gelen_bilgi=TarifFragmentArgs.fromBundle(it).bilgi
            if(gelen_bilgi.equals("menudengeldim")){
                //yeni bir yemek eklemeye geldi

                yemekismiText.setText("")
                yemekmalzameText.setText("")
                button.visibility=View.VISIBLE //button görünür

                val gorselSecmeArkaPlani=BitmapFactory.decodeResource(context?.resources,R.drawable.ic_launcher_background)
                imageView1.setImageBitmap(gorselSecmeArkaPlani)
            }else{
                //daha önce oluşturulan yemeği görmeye geldi

                button.visibility=View.INVISIBLE //buton görünmez

                val gelen_id=TarifFragmentArgs.fromBundle(it).id
                context?.let {
                    try {
                        val db=it.openOrCreateDatabase("Yemekler",Context.MODE_PRIVATE, null)
                        val cursor=db.rawQuery("SELECT * FROM yemekler WHERE id = ?", arrayOf(gelen_id.toString()))

                        val yemekIsmiIndex=cursor.getColumnIndex("yemekismi")
                        val yemekMalzemeIndex=cursor.getColumnIndex("yemekmalzemesi")
                        val yemekGorselIndex=cursor.getColumnIndex("gorsel")

                        while (cursor.moveToNext()){
                            yemekismiText.setText(cursor.getString(yemekIsmiIndex))
                            yemekmalzameText.setText(cursor.getString(yemekMalzemeIndex))


                            val byteDizisi=cursor.getBlob(yemekGorselIndex)
                            val bitmap=BitmapFactory.decodeByteArray(byteDizisi,0,byteDizisi.size)//byte dizisini bitmape çevirme
                            imageView1.setImageBitmap(bitmap)

                        }

                        cursor.close()


                    }catch (e:Exception){
                        e.printStackTrace()
                    }

                }
            }
        }
    }

    fun kaydet(view: View){

    }
    fun gorselSec(view: View){
        //izin verilip verilmediğini kontrol etme önceden verilmişse devam edilir,verilmmeişse sorulur.

        activity?.let {
            //PERMISSION_GRANTED izin verildi
            if(ContextCompat.checkSelfPermission(it.applicationContext,android.Manifest.permission.READ_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED){
                //izin verilmedi,istememez gerekior
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)//İZİN İSTEME
            }else{
                //izin verilmiş,galeriye git
                val galeri_intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeri_intent,2)
            }
        }


    }

    override fun onRequestPermissionsResult( //istenilen izinlerin sonuçları
        requestCode: Int, //istek kodu
        permissions: Array<out String>,
        grantResults: IntArray //verilern sonuçlar
    ) {
        if(requestCode==1){
            if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
               //geriye bir şey döndümü && ilk elemana izin verildiyse
                //izin alındı
                val galeri_intent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeri_intent,2)

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //sonuçları ele alma
        //                cevap kodu           kabul ederse  verinin boş olmaması
        if (requestCode==2 && resultCode==Activity.RESULT_OK && data!=null) {
            secilenGorsel=data.data //seçilen görseli alma

            try {

                context?.let {
                    if(secilenGorsel!=null){
                        if (Build.VERSION.SDK_INT>=28){
                            val source=ImageDecoder.createSource(it.contentResolver,secilenGorsel!!)
                            secilenBitmap=ImageDecoder.decodeBitmap(source)
                            imageView1.setImageBitmap(secilenBitmap)
                        }
                        else{
                            secilenBitmap=MediaStore.Images.Media.getBitmap(it.contentResolver,secilenGorsel)
                            imageView1.setImageBitmap(secilenBitmap)
                        }
                    }
                }

            }catch (e : Exception){
                e.printStackTrace()
            }


        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    //boyut küçülte fonk.

    fun kucukBitmap(kullanicininSecitigiBitmap:Bitmap,maximumBoyut:Int) : Bitmap{
        var genislik=kullanicininSecitigiBitmap.width
        var yukseklik=kullanicininSecitigiBitmap.height

        var bitmapOran:Double=genislik.toDouble()/yukseklik.toDouble()
        if(bitmapOran>1){
            //görselimiz yatay
            genislik=maximumBoyut
            val kisaltilmisYukseklik=genislik/bitmapOran
            yukseklik=kisaltilmisYukseklik.toInt()
        }else{
            //görselimzi dikey
            yukseklik=maximumBoyut
            val kisaltilmisGenislik=yukseklik*bitmapOran
            genislik=kisaltilmisGenislik.toInt()
        }

        return Bitmap.createScaledBitmap(kullanicininSecitigiBitmap,genislik,yukseklik,true)

    }
}