package com.radhsyn83.themoviedb.ui.detail.model

import com.google.gson.annotations.SerializedName


data class Credits(
    @SerializedName("cast")
    var cast: ArrayList<Cast>?,
    @SerializedName("crew")
    var crew: ArrayList<Any?>?
) {
    data class Cast(
        @SerializedName("character")
        var character: String?,
        @SerializedName("credit_id")
        var creditId: String?,
        @SerializedName("gender")
        var gender: Int?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("order")
        var order: Int?,
        @SerializedName("profile_path")
        var profilePath: String?
    )
}