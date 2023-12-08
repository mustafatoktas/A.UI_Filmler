package com.mustafatoktas.filmler.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.mustafatoktas.filmler.databinding.ActivityIntroBinding


class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun btnSimdiIzleTiklandi (view : View) {
        startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
        finish()
    }
}
