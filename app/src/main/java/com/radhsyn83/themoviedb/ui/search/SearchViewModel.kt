package com.radhsyn83.themoviedb.ui.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.radhsyn83.themoviedb.net.NetworkConfig
import com.radhsyn83.themoviedb.ui.search.model.SearchModel
import com.radhsyn83.themoviedb.utils.RetrofitHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(val mContext: Context) : ViewModel() {
    var search = MutableLiveData<ArrayList<SearchModel.Result>>()

    fun loadSearch(q: String) {
        NetworkConfig.api().search(q).enqueue(object : Callback<SearchModel> {
            override fun onFailure(call: Call<SearchModel>, t: Throwable) {
                RetrofitHandler.failure(mContext, t)
            }

            override fun onResponse(
                call: Call<SearchModel>,
                response: Response<SearchModel>
            ) {
                val res = response.body()
                RetrofitHandler.checkResponse(
                    mContext,
                    response.isSuccessful,
                    res?.success,
                    res?.statusMessage
                ) {
                    search.value = res?.results
                }
            }
        })
    }

}
