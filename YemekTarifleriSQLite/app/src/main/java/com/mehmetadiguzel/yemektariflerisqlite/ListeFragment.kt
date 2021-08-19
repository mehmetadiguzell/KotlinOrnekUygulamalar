package com.mehmetadiguzel.yemektariflerisqlite

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_liste.*

class ListeFragment : Fragment() {

    var yemekIsmiListesi =ArrayList<String>()
    var yemekIdListesi=ArrayList<Int>()
    private lateinit var listeAdapter : ListeReyclearAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeAdapter= ListeReyclearAdapter(yemekIsmiListesi,yemekIdListesi)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=listeAdapter

        sqlVeriAlma()
    }

    fun sqlVeriAlma(){
        try {
            activity?.let {
                val database=it.openOrCreateDatabase("Yemekler",Activity.MODE_PRIVATE, null)

                val cursor=database.rawQuery("SELECT * FROM yemekler",null)
                val yemekIsmiIndex=cursor.getColumnIndex("yemekismi")
                val idIndex=cursor.getColumnIndex("id")

                yemekIsmiListesi.clear()
                yemekIdListesi.clear()

                while (cursor.moveToNext()){

                    yemekIsmiListesi.add(cursor.getString(yemekIsmiIndex))
                    yemekIdListesi.add(cursor.getInt(idIndex))


                }
                listeAdapter.notifyDataSetChanged()//liste adaptore yeni veri geldiğini söyleyecek ve güncellenecek
                cursor.close()

            }
        }catch (e:Exception){
            e.printStackTrace()

        }


    }

  
}