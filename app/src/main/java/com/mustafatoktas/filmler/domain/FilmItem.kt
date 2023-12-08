package com.mustafatoktas.filmler.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FilmItem {
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var etiket: String? = null

    @Expose
    var poster: String? = null

    @SerializedName("runtime")
    @Expose
    var sure: String? = null

    @SerializedName("actors")
    @Expose
    var aktor: String? = null

    @SerializedName("plot")
    @Expose
    var ozet: String? = null

    @SerializedName("imdb_rating")
    @Expose
    var imbdPuani: String? = null

    @Expose
    var genres: List<String>? = null

    @SerializedName("images")
    @Expose
    var resimler: List<String>? = null
}
