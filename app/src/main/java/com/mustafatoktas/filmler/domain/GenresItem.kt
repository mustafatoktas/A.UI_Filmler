package com.mustafatoktas.filmler.domain

import com.google.gson.annotations.SerializedName

class GenresItem {

    @SerializedName("name")
    var isim: String? = null

    var id: Int? = null
}