package com.mustafatoktas.filmler.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mustafatoktas.filmler.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun btnGirisYapTiklandi (view : View){
        if (binding.etKullaniciAdi.text.toString().isEmpty() || binding.etParola.text.toString().isEmpty()) {
            Snackbar.make(view,"Lütfen gerekli alanları doldurun",Snackbar.LENGTH_SHORT).show()
        } else if (binding.etKullaniciAdi.text.toString() == "mustafatoktas" && binding.etParola.text.toString() == "123") {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        } else
            Snackbar.make(view,"Giriş bilgileri yanlış",Snackbar.LENGTH_SHORT).show()
    }
}
