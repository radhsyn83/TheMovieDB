package com.radhsyn83.themoviedb.ui.search.model


import com.google.gson.annotations.SerializedName

data class SearchModel(
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
        @SerializedName("first_air_date")
        var firstAirDate: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("original_name")
        var originalName: String?,
        @SerializedName("adult")
        var adult: Boolean?,
        @SerializedName("backdrop_path")
        var backdropPath: String?,
        @SerializedName("gender")
        var gender: Int?,
        @SerializedName("genre_ids")
        var genreIds: List<Any?>?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("known_for")
        var knownFor: ArrayList<KnownFor>?,
        @SerializedName("known_for_department")
        var knownForDepartment: String?,
        @SerializedName("media_type")
        var mediaType: String?,
        @SerializedName("original_language")
        var originalLanguage: String?,
        @SerializedName("original_title")
        var originalTitle: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("popularity")
        var popularity: Double?,
        @SerializedName("poster_path")
        var posterPath: Any?,
        @SerializedName("profile_path")
        var profilePath: String?,
        @SerializedName("release_date")
        var releaseDate: String?,
        @SerializedName("title")
        var title: String?,
        @SerializedName("video")
        var video: Boolean?,
        @SerializedName("vote_average")
        var voteAverage: String?,
        @SerializedName("vote_count")
        var voteCount: Int?
    ) {
        data class KnownFor(
            @SerializedName("adult")
            var adult: Boolean?,
            @SerializedName("backdrop_path")
            var backdropPath: String?,
            @SerializedName("first_air_date")
            var firstAirDate: String?,
            @SerializedName("genre_ids")
            var genreIds: ArrayList<Int>?,
            @SerializedName("id")
            var id: Int?,
            @SerializedName("media_type")
            var mediaType: String?,
            @SerializedName("name")
            var name: String?,
            @SerializedName("origin_country")
            var originCountry: ArrayList<String>?,
            @SerializedName("original_language")
            var originalLanguage: String?,
            @SerializedName("original_name")
            var originalName: String?,
            @SerializedName("original_title")
            var originalTitle: String?,
            @SerializedName("overview")
            var overview: String?,
            @SerializedName("poster_path")
            var posterPath: String?,
            @SerializedName("release_date")
            var releaseDate: String?,
            @SerializedName("title")
            var title: String?,
            @SerializedName("video")
            var video: Boolean?,
            @SerializedName("vote_average")
            var voteAverage: Double?,
            @SerializedName("vote_count")
            var voteCount: Int?
        )
    }
}