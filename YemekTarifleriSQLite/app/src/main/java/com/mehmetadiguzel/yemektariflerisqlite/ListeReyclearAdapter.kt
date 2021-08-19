package com.mehmetadiguzel.yemektariflerisqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclear_row.view.*

class ListeReyclearAdapter(val yemekListesi:ArrayList<String>,val idListesi:ArrayList<Int>) :RecyclerView.Adapter<ListeReyclearAdapter.YemekHolder>() {

    class YemekHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recyclear_row,parent,false)
        return YemekHolder(view)
    }

    override fun onBindViewHolder(holder: YemekHolder, position: Int) {
        holder.itemView.reyclear_row_text.text=yemekListesi[position]
        holder.itemView.setOnClickListener {
            val action=ListeFragmentDirections.actionListeFragmentToTarifFragment("reyclerdengeldim",idListesi[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }
}