package com.radhsyn83.themoviedb.ui.person

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.radhsyn83.themoviedb.net.NetworkConfig
import com.radhsyn83.themoviedb.ui.detail.model.DetailCastModel
import com.radhsyn83.themoviedb.ui.detail.model.DetailTvModel
import com.radhsyn83.themoviedb.ui.person.model.PersonModel
import com.radhsyn83.themoviedb.utils.RetrofitHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonViewModel(val mContext: Context) : ViewModel() {
    var person = MutableLiveData<PersonModel>()

    fun load(id: Int) {
        NetworkConfig.api()
            .person(id)
            .enqueue(object : Callback<PersonModel> {
                override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                    RetrofitHandler.failure(mContext, t)
                }

                override fun onResponse(
                    call: Call<PersonModel>,
                    response: Response<PersonModel>
                ) {
                    val res = response.body()
                    RetrofitHandler.checkResponse(
                        mContext,
                        response.isSuccessful,
                        res?.success,
                        res?.statusMessage
                    ) {
                        person.value = res
                    }
                }
            })
    }
}
