package com.radhsyn83.themoviedb.ui.person.model


import com.google.gson.annotations.SerializedName

data class PersonModel(
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var statusMessage: String?,
    @SerializedName("success")
    var success: Boolean? = true,
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("also_known_as")
    var alsoKnownAs: ArrayList<String>?,
    @SerializedName("biography")
    var biography: String?,
    @SerializedName("birthday")
    var birthday: String?,
    @SerializedName("combined_credits")
    var combinedCredits: CombinedCredits?,
    @SerializedName("deathday")
    var deathday: Any?,
    @SerializedName("gender")
    var gender: Int?,
    @SerializedName("homepage")
    var homepage: Any?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("imdb_id")
    var imdbId: String?,
    @SerializedName("known_for_department")
    var knownForDepartment: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("place_of_birth")
    var placeOfBirth: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("profile_path")
    var profilePath: String?
) {
    data class CombinedCredits(
        @SerializedName("cast")
        var cast: ArrayList<Cast>?,
        @SerializedName("crew")
        var crew: ArrayList<Crew?>?
    ) {
        data class Cast(
            @SerializedName("adult")
            var adult: Boolean?,
            @SerializedName("backdrop_path")
            var backdropPath: Any?,
            @SerializedName("character")
            var character: String?,
            @SerializedName("credit_id")
            var creditId: String?,
            @SerializedName("episode_count")
            var episodeCount: Int?,
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
            var voteCount: Int?
        )

        data class Crew(
            @SerializedName("backdrop_path")
            var backdropPath: String?,
            @SerializedName("credit_id")
            var creditId: String?,
            @SerializedName("department")
            var department: String?,
            @SerializedName("episode_count")
            var episodeCount: Int?,
            @SerializedName("first_air_date")
            var firstAirDate: String?,
            @SerializedName("genre_ids")
            var genreIds: ArrayList<Int>?,
            @SerializedName("id")
            var id: Int?,
            @SerializedName("job")
            var job: String?,
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
            @SerializedName("overview")
            var overview: String?,
            @SerializedName("popularity")
            var popularity: Double?,
            @SerializedName("poster_path")
            var posterPath: String?,
            @SerializedName("vote_average")
            var voteAverage: Double?,
            @SerializedName("vote_count")
            var voteCount: Int?
        )
    }
}