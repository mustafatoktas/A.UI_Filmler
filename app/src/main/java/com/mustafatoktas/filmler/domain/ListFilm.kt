package com.mustafatoktas.filmler.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListFilm {

    @SerializedName("data")
    @Expose
    var veri: List<Datum>? = null
}