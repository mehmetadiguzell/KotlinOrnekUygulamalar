package com.mehmetadiguzel.superkahramankitabi

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclear_view.view.*

class RecyclearAdapter(val kahramanListesi : ArrayList<String>,val kahramanGorsel : ArrayList<Int>) : RecyclerView.Adapter<RecyclearAdapter.SuperKahramanVH>() {

    class SuperKahramanVH(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperKahramanVH {
        //inflater xml layouta bağlama
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.recyclear_view,parent,false)
        return SuperKahramanVH(itemView)

    }

    override fun onBindViewHolder(holder: SuperKahramanVH, position: Int) {


        holder.itemView.recyclerText.text=kahramanListesi.get(position)
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,TanitimActivity::class.java)
            intent.putExtra("kahramanismi",kahramanListesi.get(position))
            intent.putExtra("secilen",kahramanGorsel.get(position))

            /*
            val singleton=SingletonClass.SecilenKahraman
            singleton.gorsel=kahramanGorsel.get(position)

             */

            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return kahramanListesi.size

    }
}