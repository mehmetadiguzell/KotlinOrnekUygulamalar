package com.mehmetadiguzel.besinlerkitabi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mehmetadiguzel.besinlerkitabi.R
import com.mehmetadiguzel.besinlerkitabi.databinding.BesinRecyclearRowBinding
import com.mehmetadiguzel.besinlerkitabi.model.Besin
import com.mehmetadiguzel.besinlerkitabi.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_recyclear_row.view.*

class BesinAdapter(val besinListesi:ArrayList<Besin>):RecyclerView.Adapter<BesinAdapter.BesinViewHolder>(), BesinClickListener {

    class BesinViewHolder(var view: BesinRecyclearRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        //görünüm bağlama
        val inflater=LayoutInflater.from(parent.context)
        //val view=inflater.inflate(R.layout.besin_recyclear_row,parent,false)
        val view=DataBindingUtil.inflate<BesinRecyclearRowBinding>(inflater,R.layout.besin_recyclear_row,parent,false)
        return BesinViewHolder(view)

    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {

        holder.view.besin=besinListesi[position]
        holder.view.listener=this


        /*
       //kaç recyclear oluşma
        holder.itemView.isim.text=besinListesi.get(position).besinIsim
        holder.itemView.kalori.text=besinListesi.get(position).besinKalori
        //görsel eklenecek

        holder.itemView.setOnClickListener {
            val action=BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.imageView.gorselIndir(besinListesi.get(position).besinGorsel, placeholderYap(holder.itemView.context))

         */
    }

    override fun getItemCount(): Int {
        //görünüme erişme
        return besinListesi.size
    }
    fun besinListesiGuncelle(yeniBesinListesi:List<Besin>){
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()

    }

    override fun besinTiklandi(view: View) {
        val uuid=Integer.valueOf(view.besinUuid.text.toString())
        val action=BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayiFragment(uuid)
        Navigation.findNavController(view).navigate(action)

        super.besinTiklandi(view)
    }
}