package com.radhsyn83.themoviedb.utils

import android.content.Context
import com.radhsyn83.themoviedb.R
import org.jetbrains.anko.*
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//
// Created by Fathur Radhy 
// on May 2019-05-31.
//
object RetrofitHandler {
    fun checkResponse(
        mContext: Context,
        success: Boolean,
        error: Boolean?,
        msg: String?,
        onSuccess: () -> Unit
    ) {
        if (success) {
            error?.let { e ->
                if (e) {
                    onSuccess()
                } else {
                    mContext.toast(msg.toString())
                    return
                }
            }
            onSuccess()
        } else {
            mContext.toast(msg.toString())
        }
    }

    fun failure(mContext: Context, t: Throwable) {
        if (t is SocketTimeoutException) {
            mContext.toast(mContext.resources.getString(R.string.rto))
        } else if (t is ConnectException) {
            mContext.toast(mContext.resources.getString(R.string.no_internet))
        } else if (t is UnknownHostException) {
            mContext.toast(mContext.resources.getString(R.string.unknown_server))
        } else {
            if (t.message != null) {
                mContext.toast(t.message.toString())
            }
        }
    }
}