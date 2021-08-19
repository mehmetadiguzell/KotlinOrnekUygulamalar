package com.mehmetadiguzel.firebaseexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_haberler.*

class HaberlerActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database:FirebaseFirestore
    private lateinit var recyclearViewAdapter:HaberRecyclearAdapter
    var postListesi=ArrayList<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haberler)
        auth= FirebaseAuth.getInstance()
        database= FirebaseFirestore.getInstance()

        verileriAl()
        val layoutManager=LinearLayoutManager(this)
        recyclerHaberler.layoutManager=layoutManager
        recyclearViewAdapter= HaberRecyclearAdapter(postListesi)
        recyclerHaberler.adapter=recyclearViewAdapter
    }

    fun verileriAl(){
        database.collection("Post").orderBy("tarih",Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
            if(error !=null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if (snapshot!=null){
                    if (!snapshot.isEmpty){
                        val document=snapshot.documents
                        postListesi.clear()

                        for (documen in document){
                            val kullaniciEmail=documen.get("kullaniciemail") as String
                            val kullaniciYorum=documen.get("kullaniciyorum") as String
                            val gorselUrl=documen.get("dowloandurl") as String

                            val indirilenPost=Post(kullaniciEmail,kullaniciYorum,gorselUrl)
                            postListesi.add(indirilenPost)
                        }
                        recyclearViewAdapter.notifyDataSetChanged()
                    }
                }

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater=menuInflater
        inflater.inflate(R.menu.secenekler_menusu,menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.fotograf_paylas){
            //fotograf paylaş activiysine gidilecek
            val intent=Intent(this,FotografPaylasma::class.java)
            startActivity(intent)
        }
        else if (item.itemId==R.id.cikis_yap){
            //çıkış yapılacak
                auth.signOut()
            val intent=Intent(this,KullaniciActivity::class.java)
            startActivity(intent)
            finish()

        }
        return super.onOptionsItemSelected(item)
    }
}






