package com.mustafatoktas.filmler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mustafatoktas.filmler.R

class AdapterAktorListesi (private val resimlerListesi: List<String>) : RecyclerView.Adapter<ViewHolderAktorListesi>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAktorListesi {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_aktor, parent, false)
        return ViewHolderAktorListesi(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolderAktorListesi, position: Int) {
        Glide.with(context).load(resimlerListesi[position]).into(holder.imgAktor)
    }

    override fun getItemCount() = resimlerListesi.size

}

class ViewHolderAktorListesi(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgAktor: ImageView = itemView.findViewById(R.id.imgAktor)
}
