package com.radhsyn83.themoviedb.ui.home.model


import com.google.gson.annotations.SerializedName

data class HomeTvPopularModel(
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var statusMessage: String?,
    @SerializedName("success")
    var success: Boolean? = true,
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: ArrayList<Result>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
) {
    data class Result(
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @SerializedName("first_air_date")
        var firstAirDate: String?,
        @SerializedName("genre_ids")
        var genreIds: List<Int?>?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("origin_country")
        var originCountry: List<String?>?,
        @SerializedName("original_language")
        var originalLanguage: String?,
        @SerializedName("original_name")
        var originalName: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("popularity")
        var popularity: Double?,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("vote_average")
        var voteAverage: String?,
        @SerializedName("vote_count")
        var voteCount: Int?
    )
}