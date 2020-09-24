package com.radhsyn83.themoviedb.ui.home.model


import com.google.gson.annotations.SerializedName

data class HomeMovieModel(
    @SerializedName("dates")
    var dates: Dates?,
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: ArrayList<Result>?,
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var statusMessage: String?,
    @SerializedName("success")
    var success: Boolean? = true,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
) {
    data class Dates(
        @SerializedName("maximum")
        var maximum: String?,
        @SerializedName("minimum")
        var minimum: String?
    )

    data class Result(
        @SerializedName("adult")
        var adult: Boolean?,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @SerializedName("genre_ids")
        var genreIds: ArrayList<Int>?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("original_language")
        var originalLanguage: String?,
        @SerializedName("original_title")
        var originalTitle: String?,
        @SerializedName("original_name")
        var originalName: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("popularity")
        var popularity: Double?,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("release_date")
        var releaseDate: String?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("video")
        var video: Boolean?,
        @SerializedName("vote_average")
        var voteAverage: String?,
        @SerializedName("vote_count")
        var voteCount: String?
    )
}