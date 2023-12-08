package com.mustafatoktas.filmler.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mustafatoktas.filmler.R

class AdapterKategoriyeGoreFilmListesi (private val itemListesi: List<String>) : RecyclerView.Adapter<ViewHolderKategoriyeGoreFilmListesi>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderKategoriyeGoreFilmListesi {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_kategori, parent, false)
        return ViewHolderKategoriyeGoreFilmListesi(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolderKategoriyeGoreFilmListesi, position: Int) {
        holder.txtKategori.text = itemListesi[position]
    }

    override fun getItemCount() = itemListesi.size



}

class ViewHolderKategoriyeGoreFilmListesi(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtKategori: TextView = itemView.findViewById(R.id.txtKategori)
}
