package com.mustafatoktas.filmler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mustafatoktas.filmler.R
import com.mustafatoktas.filmler.domain.KaydirItemleri

class AdapterKaydir(private val kaydirItems: MutableList<KaydirItemleri>, private val viewPager2: ViewPager2) : RecyclerView.Adapter<ViewHolderKaydir>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderKaydir {
        context = parent.context
        return ViewHolderKaydir(LayoutInflater.from(parent.context).inflate(R.layout.item_kaydir, parent, false), context)
    }

    override fun onBindViewHolder(holder: ViewHolderKaydir, position: Int) {
        holder.resimAyarla(kaydirItems[position])
        if (position == kaydirItems.size - 2) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount() = kaydirItems.size


    private val runnable = Runnable {
        kaydirItems.addAll(kaydirItems)
        notifyDataSetChanged()
    }
}

class ViewHolderKaydir (itemView: View , val context: Context) : RecyclerView.ViewHolder(itemView) {
    private val imgKaydir: ImageView = itemView.findViewById(R.id.imgKaydir)

    fun resimAyarla(sliderItems: KaydirItemleri) {
        val istekSecenekleri = RequestOptions().transforms(CenterCrop(), RoundedCorners(60))
        Glide.with(context)
            .load(sliderItems.resim)
            .apply(istekSecenekleri)
            .into(imgKaydir)
    }
}
