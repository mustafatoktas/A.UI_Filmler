package com.mustafatoktas.filmler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mustafatoktas.filmler.R
import com.mustafatoktas.filmler.domain.GenresItem

class AdapterKategoriListesi(private val itemListesi: ArrayList<GenresItem>) : RecyclerView.Adapter<ViewHolderAdapterKategoriListesi>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapterKategoriListesi {
        context = parent.context
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_kategori, parent, false)
        return ViewHolderAdapterKategoriListesi(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolderAdapterKategoriListesi, position: Int) {
        holder.txtKategori.text = itemListesi[position].isim
    }

    override fun getItemCount() = itemListesi.size

}

class ViewHolderAdapterKategoriListesi (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtKategori: TextView = itemView.findViewById(R.id.txtKategori)
}
