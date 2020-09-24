package com.radhsyn83.themoviedb.ui.detail.model


import com.google.gson.annotations.SerializedName

data class DetailCastModel(
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var statusMessage: String?,
    @SerializedName("success")
    var success: Boolean? = true,
    @SerializedName("cast")
    var cast: ArrayList<Cast>?,
    @SerializedName("crew")
    var crew: ArrayList<Crew>?,
    @SerializedName("id")
    var id: Int?
) {
    data class Cast(
        @SerializedName("cast_id")
        var castId: Int?,
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

    data class Crew(
        @SerializedName("credit_id")
        var creditId: String?,
        @SerializedName("department")
        var department: String?,
        @SerializedName("gender")
        var gender: Int?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("job")
        var job: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("profile_path")
        var profilePath: String?
    )
}