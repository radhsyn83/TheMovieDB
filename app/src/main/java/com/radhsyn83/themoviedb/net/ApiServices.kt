package com.radhsyn83.themoviedb.net

import com.radhsyn83.themoviedb.BuildConfig.API_KEY
import com.radhsyn83.themoviedb.ui.detail.model.DetailCastModel
import com.radhsyn83.themoviedb.ui.detail.model.DetailMoviesModel
import com.radhsyn83.themoviedb.ui.detail.model.DetailTvModel
import com.radhsyn83.themoviedb.ui.home.model.HomeMovieModel
import com.radhsyn83.themoviedb.ui.home.model.HomeTvPopularModel
import com.radhsyn83.themoviedb.ui.person.model.PersonModel
import com.radhsyn83.themoviedb.ui.search.model.SearchModel
import retrofit2.Call
import retrofit2.http.*


//
// Created by Fathur Radhy
// on May 2019-05-27.
//
interface ApiServices {

    @GET("trending/tv/day")
    fun trendingDay(
        @Query("api_key") id_customer: String = API_KEY
    ): Call<HomeMovieModel>

    @GET("trending/tv/week")
    fun trendingWeek(
        @Query("api_key") id_customer: String = API_KEY
    ): Call<HomeMovieModel>

    @GET("tv/popular")
    fun popular(
        @Query("api_key") id_customer: String = API_KEY
    ): Call<HomeTvPopularModel>

    @GET("movie/upcoming")
    fun upcoming(
        @Query("api_key") id_customer: String = API_KEY
    ): Call<HomeMovieModel>

    @GET("tv/{id}")
    fun tvDetail(
        @Path("id") id: Int,
        @Query("api_key") id_customer: String = API_KEY,
        @Query("append_to_response") append: String = "credits,videos,recommendations"
    ): Call<DetailTvModel>

    @GET("person/{id}")
    fun person(
        @Path("id") id: Int,
        @Query("api_key") id_customer: String = API_KEY,
        @Query("append_to_response") append: String = "combined_credits"
    ): Call<PersonModel>

    @GET("movie/{id}")
    fun movieDetail(
        @Path("id") id: Int,
        @Query("api_key") id_customer: String = API_KEY,
        @Query("append_to_response") append: String = "credits,videos,recommendations"
    ): Call<DetailTvModel>

    @GET("search/multi")
    fun search(
        @Query("query") query: String,
        @Query("api_key") id_customer: String = API_KEY
    ): Call<SearchModel>

}
