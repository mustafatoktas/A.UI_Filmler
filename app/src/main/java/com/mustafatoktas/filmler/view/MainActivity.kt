package com.mustafatoktas.filmler.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mustafatoktas.filmler.R
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mustafatoktas.filmler.adapter.AdapterFilmListesi
import com.mustafatoktas.filmler.adapter.AdapterKategoriListesi
import com.mustafatoktas.filmler.adapter.AdapterKaydir
import com.mustafatoktas.filmler.databinding.ActivityMainBinding
import com.mustafatoktas.filmler.domain.GenresItem
import com.mustafatoktas.filmler.domain.KaydirItemleri
import com.mustafatoktas.filmler.domain.ListFilm
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var adapterEnIiyiFilmler: RecyclerView.Adapter<*>
    private lateinit var adapterCikacaklar: RecyclerView.Adapter<*>
    private lateinit var adapterKategori: RecyclerView.Adapter<*>
    private lateinit var fIstekKuyrugu: RequestQueue
    private lateinit var fStringIstegi: StringRequest
    private lateinit var fStringIstegi2: StringRequest
    private lateinit var fStringIstegi3: StringRequest
    private val kaydirHandler = Handler()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ayarlamalar()
        kapaklar()
        gonderIstekEnIyiFilmler()
        gonderIstekCikacaklar()
        gonderIstekKategoriler()
    }

    private fun gonderIstekEnIyiFilmler() {
        fIstekKuyrugu = Volley.newRequestQueue(this)
        binding.progressBar1.visibility = View.VISIBLE
        fStringIstegi = StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1",
            { response ->
                val gson = Gson()
                binding.progressBar1.visibility = View.GONE
                val itemler = gson.fromJson(response, ListFilm::class.java)
                adapterEnIiyiFilmler = AdapterFilmListesi(itemler)
                binding.view1.adapter = adapterEnIiyiFilmler
            },
            { error ->
                binding.progressBar1.visibility = View.GONE
                Log.i("Filmler", "Hata Mesajı: $error")
            })
        fIstekKuyrugu.add(fStringIstegi)
    }

    private fun gonderIstekCikacaklar() {
        fIstekKuyrugu = Volley.newRequestQueue(this)
        binding.progressBar3.visibility = View.VISIBLE
        fStringIstegi3 = StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2",
            { response ->
                val gson = Gson()
                binding.progressBar3.visibility = View.GONE
                val itemler = gson.fromJson(response, ListFilm::class.java)
                adapterCikacaklar = AdapterFilmListesi(itemler)
                binding.view3.adapter = adapterCikacaklar
            },
            { error ->
                binding.progressBar3.visibility = View.GONE
                Log.i("Filmler", "Hata Mesajı: $error")
            })
        fIstekKuyrugu.add(fStringIstegi3)
    }

    private fun gonderIstekKategoriler() {
        fIstekKuyrugu = Volley.newRequestQueue(this)
        binding.progressBar2.visibility = View.VISIBLE
        fStringIstegi2 = StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres",
            { response ->
                val gson = Gson()
                binding.progressBar2.visibility = View.GONE
                val kategoriList: ArrayList<GenresItem> = gson.fromJson(
                    response,
                    object : TypeToken<ArrayList<GenresItem>>() {}.type
                )
                adapterKategori = AdapterKategoriListesi(kategoriList)
                binding.view2.adapter = adapterKategori
            },
            { error ->
                binding.progressBar2.visibility = View.GONE
                Log.i("Filmler", "Hata Mesajı: $error")
            })
        fIstekKuyrugu.add(fStringIstegi2)
    }

    private fun kapaklar() {
        val kaydirItemleri: MutableList<KaydirItemleri> = mutableListOf(
            KaydirItemleri(R.drawable.kapak1),
            KaydirItemleri(R.drawable.kapak2),
            KaydirItemleri(R.drawable.kapak3)
        )

        binding.viewpagerKaydir.adapter = AdapterKaydir(kaydirItemleri, binding.viewpagerKaydir)
        binding.viewpagerKaydir.clipToPadding = false
        binding.viewpagerKaydir.clipChildren = false
        binding.viewpagerKaydir.offscreenPageLimit = 3
        binding.viewpagerKaydir.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val kompozitSAyfaTrafosu = CompositePageTransformer()
        kompozitSAyfaTrafosu.addTransformer(MarginPageTransformer(40))
        kompozitSAyfaTrafosu.addTransformer { sayfa, position ->
            val r = 1 - abs(position)
            sayfa.scaleY = 0.85f + r * 0.15f
        }

        binding.viewpagerKaydir.setPageTransformer(kompozitSAyfaTrafosu)
        binding.viewpagerKaydir.currentItem = 1
        binding.viewpagerKaydir.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                kaydirHandler.removeCallbacks(kaydirRunnable)
            }
        })
    }

    private val kaydirRunnable = Runnable {
        binding.viewpagerKaydir.currentItem = binding.viewpagerKaydir.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        kaydirHandler.removeCallbacks(kaydirRunnable)
    }

    override fun onResume() {
        super.onResume()
        kaydirHandler.postDelayed(kaydirRunnable, 2000)
    }

    private fun ayarlamalar() {
        binding.view1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.view3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.view2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}


