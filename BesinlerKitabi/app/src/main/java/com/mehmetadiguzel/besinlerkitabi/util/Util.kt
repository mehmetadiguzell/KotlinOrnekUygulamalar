package com.mehmetadiguzel.besinlerkitabi.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mehmetadiguzel.besinlerkitabi.R

fun ImageView.gorselIndir(url : String?,placeholder: CircularProgressDrawable){
    val options=RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher)
Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun placeholderYap(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=38f
        start()
    }
}
@BindingAdapter("android:dowloandImage")
fun dowloandImage(view: ImageView,url: String?){
    view.gorselIndir(url, placeholderYap(view.context))

}
