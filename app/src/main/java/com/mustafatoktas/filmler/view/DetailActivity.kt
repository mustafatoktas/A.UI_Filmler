package com.mustafatoktas.filmler.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mustafatoktas.filmler.adapter.AdapterAktorListesi
import com.mustafatoktas.filmler.adapter.AdapterKategoriyeGoreFilmListesi
import com.mustafatoktas.filmler.databinding.ActivityDetailBinding
import com.mustafatoktas.filmler.domain.FilmItem

class DetailActivity : AppCompatActivity() {
    private lateinit var fIstekKuyrugu: RequestQueue
    private lateinit var fStringIstegi: StringRequest
    private var idFilm: Int = 0
    private lateinit var adapterAktorListesi: RecyclerView.Adapter<*>
    private lateinit var adapterKategoriListesi: RecyclerView.Adapter<*>


    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idFilm = intent.getIntExtra("id", 0)
        ayarlamalar()
        gonderIstek()
    }

    private fun gonderIstek() {
        fIstekKuyrugu = Volley.newRequestQueue(this)
        binding.progressBarDetay.visibility = View.VISIBLE
        binding.scrollView2.visibility = View.GONE

        fStringIstegi = StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/$idFilm",
            { response ->
                val gson = Gson()
                binding.progressBarDetay.visibility = View.GONE
                binding.scrollView2.visibility = View.VISIBLE

                val item: FilmItem = gson.fromJson(response, FilmItem::class.java)

                Glide.with(this@DetailActivity)
                    .load(item.poster)
                    .into(binding.imgDetay)

                binding.txtFilmAdi .text = item.etiket
                binding.txtFilmYildiz.text = item.imbdPuani
                binding.txtSure.text = item.sure
                binding.txtOzet.text = item.ozet
                binding.txtAktorBilgi.text = item.aktor
                if (item.resimler != null) {
                    adapterAktorListesi = AdapterAktorListesi(item.resimler!!)
                    binding.recyclerResimler.adapter = adapterAktorListesi
                }
                if (item.genres != null) {
                    adapterKategoriListesi = AdapterKategoriyeGoreFilmListesi(item.genres!!)
                    binding.genreView.adapter = adapterKategoriListesi
                }
            },
            { error -> binding.progressBarDetay.visibility = View.GONE })

        fIstekKuyrugu.add(fStringIstegi)
    }

    private fun ayarlamalar() {
        binding.recyclerResimler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.genreView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.imgGeri.setOnClickListener { finish() }
    }
}
