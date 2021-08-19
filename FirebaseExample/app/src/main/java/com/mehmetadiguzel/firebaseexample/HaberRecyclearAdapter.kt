package com.mehmetadiguzel.firebaseexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclear_row.view.*

class HaberRecyclearAdapter(val postList:ArrayList<Post>) : RecyclerView.Adapter<HaberRecyclearAdapter.PostHolder>(){

    class PostHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recyclear_row,parent,false)
        return PostHolder(view)

    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.itemView.recyclear_email.text=postList[position].kullaniciEmail
        holder.itemView.recyclear_yorum.text=postList[position].kullaniciYorum
        Picasso.get().load(postList[position].gorselUrl).into(holder.itemView.recyclear_image)

    }

    override fun getItemCount(): Int {
        return postList.size
    }
}


