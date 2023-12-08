package com.mustafatoktas.filmler.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Datum {

    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var etiket: String? = null

    @Expose
    var poster: String? = null
}
