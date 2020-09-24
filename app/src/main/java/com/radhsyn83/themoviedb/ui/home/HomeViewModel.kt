package com.radhsyn83.themoviedb.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.net.NetworkConfig
import com.radhsyn83.themoviedb.ui.home.model.HomeMovieModel
import com.radhsyn83.themoviedb.ui.home.model.HomeTvPopularModel
import com.radhsyn83.themoviedb.utils.RetrofitHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val mContext: Context) : ViewModel() {
    var popular = MutableLiveData<ArrayList<HomeTvPopularModel.Result>>()
    var upcoming = MutableLiveData<ArrayList<HomeMovieModel.Result>>()
    var trending = MutableLiveData<ArrayList<HomeMovieModel.Result>>()

    init {
        loadPopular()
        loadUpcoming()
        loadTranding(true)
    }

    fun loadTranding(isToday: Boolean) {
        var conf = NetworkConfig.api().trendingDay()

        if (!isToday)
            conf = NetworkConfig.api().trendingWeek()

        conf.enqueue(object : Callback<HomeMovieModel> {
                override fun onFailure(call: Call<HomeMovieModel>, t: Throwable) {
                    RetrofitHandler.failure(mContext, t)
                }

                override fun onResponse(
                    call: Call<HomeMovieModel>,
                    response: Response<HomeMovieModel>
                ) {
                    val res = response.body()
                    RetrofitHandler.checkResponse(
                        mContext,
                        response.isSuccessful,
                        res?.success,
                        res?.statusMessage
                    ) {
                        trending.value = res?.results
                    }
                }
            })
    }

    fun loadPopular() {
        NetworkConfig.api()
            .popular()
            .enqueue(object : Callback<HomeTvPopularModel> {
                override fun onFailure(call: Call<HomeTvPopularModel>, t: Throwable) {
                    RetrofitHandler.failure(mContext, t)
                }

                override fun onResponse(
                    call: Call<HomeTvPopularModel>,
                    response: Response<HomeTvPopularModel>
                ) {
                    val res = response.body()
                    RetrofitHandler.checkResponse(
                        mContext,
                        response.isSuccessful,
                        res?.success,
                        res?.statusMessage
                    ) {
                        popular.value = res?.results
                    }
                }
            })
    }

    fun loadUpcoming() {
        NetworkConfig.api()
            .upcoming()
            .enqueue(object : Callback<HomeMovieModel> {
                override fun onFailure(call: Call<HomeMovieModel>, t: Throwable) {
                    RetrofitHandler.failure(mContext, t)
                }

                override fun onResponse(
                    call: Call<HomeMovieModel>,
                    response: Response<HomeMovieModel>
                ) {
                    val res = response.body()
                    RetrofitHandler.checkResponse(
                        mContext,
                        response.isSuccessful,
                        res?.success,
                        res?.statusMessage
                    ) {
                        upcoming.value = res?.results
                    }
                }
            })
    }

}
