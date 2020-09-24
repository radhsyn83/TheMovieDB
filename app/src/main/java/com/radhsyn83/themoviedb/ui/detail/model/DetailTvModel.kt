package com.radhsyn83.themoviedb.ui.detail.model


import com.google.gson.annotations.SerializedName

data class DetailTvModel(
    @SerializedName("status_code")
    var statusCode: Int?,
    @SerializedName("status_message")
    var statusMessage: String?,
    @SerializedName("success")
    var success: Boolean? = true,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("created_by")
    var createdBy: ArrayList<CreatedBy>?,
    @SerializedName("credits")
    var credits: Credits?,
    @SerializedName("episode_run_time")
    var episodeRunTime: ArrayList<Int>?,
    @SerializedName("runtime")
    var runtime: Int?,
    @SerializedName("first_air_date")
    var firstAirDate: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("genres")
    var genres: ArrayList<Genre>?,
    @SerializedName("homepage")
    var homepage: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("in_production")
    var inProduction: Boolean?,
    @SerializedName("languages")
    var languages: ArrayList<String>?,
    @SerializedName("last_air_date")
    var lastAirDate: String?,
    @SerializedName("last_episode_to_air")
    var lastEpisodeToAir: LastEpisodeToAir?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("networks")
    var networks: ArrayList<Network>?,
    @SerializedName("next_episode_to_air")
    var nextEpisodeToAir: Any?,
    @SerializedName("number_of_episodes")
    var numberOfEpisodes: Int?,
    @SerializedName("number_of_seasons")
    var numberOfSeasons: Int?,
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
    @SerializedName("production_companies")
    var productionCompanies: ArrayList<ProductionCompany>?,
    @SerializedName("recommendations")
    var recommendations: Recommendations?,
    @SerializedName("seasons")
    var seasons: ArrayList<Season>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("videos")
    var videos: Videos?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?
) {
    data class CreatedBy(
        @SerializedName("credit_id")
        var creditId: String?,
        @SerializedName("gender")
        var gender: Int?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("profile_path")
        var profilePath: Any?
    )

    data class Genre(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?
    )

    data class LastEpisodeToAir(
        @SerializedName("air_date")
        var airDate: String?,
        @SerializedName("episode_number")
        var episodeNumber: Int?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("production_code")
        var productionCode: String?,
        @SerializedName("season_number")
        var seasonNumber: Int?,
        @SerializedName("show_id")
        var showId: Int?,
        @SerializedName("still_path")
        var stillPath: String?,
        @SerializedName("vote_average")
        var voteAverage: Double?,
        @SerializedName("vote_count")
        var voteCount: Int?
    )

    data class Network(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("logo_path")
        var logoPath: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("origin_country")
        var originCountry: String?
    )

    data class ProductionCompany(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("logo_path")
        var logoPath: Any?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("origin_country")
        var originCountry: String?
    )

    data class Season(
        @SerializedName("air_date")
        var airDate: String?,
        @SerializedName("episode_count")
        var episodeCount: Int?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("overview")
        var overview: String?,
        @SerializedName("poster_path")
        var posterPath: String?,
        @SerializedName("season_number")
        var seasonNumber: Int?
    )

    data class Videos(
        @SerializedName("results")
        var results: ArrayList<Result?>?
    ) {
        data class Result(
            @SerializedName("id")
            var id: String?,
            @SerializedName("iso_3166_1")
            var iso31661: String?,
            @SerializedName("iso_639_1")
            var iso6391: String?,
            @SerializedName("key")
            var key: String?,
            @SerializedName("name")
            var name: String?,
            @SerializedName("site")
            var site: String?,
            @SerializedName("size")
            var size: Int?,
            @SerializedName("type")
            var type: String?
        )
    }
}