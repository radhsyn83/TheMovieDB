package com.radhsyn83.themoviedb.ui.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.radhsyn83.themoviedb.net.NetworkConfig
import com.radhsyn83.themoviedb.ui.detail.model.DetailCastModel
import com.radhsyn83.themoviedb.ui.detail.model.DetailTvModel
import com.radhsyn83.themoviedb.utils.RetrofitHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(val mContext: Context) : ViewModel() {
    var tv = MutableLiveData<DetailTvModel>()
    var tvCast = MutableLiveData<DetailCastModel>()
    var movie = MutableLiveData<DetailTvModel>()

    fun loadTv(id: Int) {
        NetworkConfig.api()
            .tvDetail(id)
            .enqueue(object : Callback<DetailTvModel> {
                override fun onFailure(call: Call<DetailTvModel>, t: Throwable) {
                    RetrofitHandler.failure(mContext, t)
                }

                override fun onResponse(
                    call: Call<DetailTvModel>,
                    response: Response<DetailTvModel>
                ) {
                    val res = response.body()
                    RetrofitHandler.checkResponse(
                        mContext,
                        response.isSuccessful,
                        res?.success,
                        res?.statusMessage
                    ) {
                        tv.value = res
                    }
                }
            })
    }

    fun loadMovies(id: Int) {
        NetworkConfig.api()
            .movieDetail(id)
            .enqueue(object : Callback<DetailTvModel> {
                override fun onFailure(call: Call<DetailTvModel>, t: Throwable) {
                    RetrofitHandler.failure(mContext, t)
                }

                override fun onResponse(
                    call: Call<DetailTvModel>,
                    response: Response<DetailTvModel>
                ) {
                    val res = response.body()
                    RetrofitHandler.checkResponse(
                        mContext,
                        response.isSuccessful,
                        res?.success,
                        res?.statusMessage
                    ) {
                        movie.value = res
                    }
                }
            })
    }

}
