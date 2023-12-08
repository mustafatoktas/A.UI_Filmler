package com.mustafatoktas.filmler.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafatoktas.filmler.R
import com.mustafatoktas.filmler.domain.ListFilm
import com.mustafatoktas.filmler.view.DetailActivity

class AdapterFilmListesi (private val itemListesi: ListFilm) : RecyclerView.Adapter<ViewHolderFilmListesi>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFilmListesi {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_film, parent, false)
        return ViewHolderFilmListesi(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolderFilmListesi, position: Int) {
        holder.txtIsim.text = itemListesi.veri!![position].etiket

        val istekSecenekleri = RequestOptions().transform(CenterCrop(), RoundedCorners(30))

        Glide.with(context)
            .load(itemListesi.veri!![position].poster)
            .apply(istekSecenekleri)
            .into(holder.imgResim)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id", itemListesi.veri!![position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = itemListesi.veri!!.size

}

class ViewHolderFilmListesi(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtIsim: TextView = itemView.findViewById(R.id.txtIsim)
    val imgResim: ImageView = itemView.findViewById(R.id.imgResim)
}
