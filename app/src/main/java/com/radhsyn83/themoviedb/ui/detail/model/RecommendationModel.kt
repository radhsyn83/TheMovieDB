package com.radhsyn83.themoviedb.ui.detail.model

import com.google.gson.annotations.SerializedName


data class Recommendations(
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
        var genreIds: ArrayList<Int>?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("networks")
        var networks: ArrayList<Network>?,
        @SerializedName("origin_country")
        var originCountry: ArrayList<String>?,
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
    ) {
        data class Network(
            @SerializedName("id")
            var id: Int?,
            @SerializedName("logo")
            var logo: Logo?,
            @SerializedName("name")
            var name: String?,
            @SerializedName("origin_country")
            var originCountry: String?
        ) {
            data class Logo(
                @SerializedName("aspect_ratio")
                var aspectRatio: Double?,
                @SerializedName("path")
                var path: String?
            )
        }
    }
}