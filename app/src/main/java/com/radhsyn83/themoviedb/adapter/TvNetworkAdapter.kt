package com.radhsyn83.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.ui.detail.model.DetailTvModel
import com.radhsyn83.themoviedb.ui.home.model.HomeMovieModel
import com.radhsyn83.themoviedb.utils.Tools
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.image
import kotlinx.android.synthetic.main.item_movie.view.tv_rating
import kotlinx.android.synthetic.main.item_movie.view.tv_release_date
import kotlinx.android.synthetic.main.item_movie.view.tv_title
import kotlinx.android.synthetic.main.item_movie_trending.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

//
// Created by Fathur Radhy 
// on May 2019-05-28.
//

class TvNetworkAdapter(
    val context: Context,
    val data: ArrayList<DetailTvModel.Network>
) : RecyclerView.Adapter<TvNetworkAdapter.ViewHolder>() {

    val movies: ArrayList<DetailTvModel.Network> = data

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(result: DetailTvModel.Network) {
            if (result.logoPath != "")
                Tools.displayImageFromUrl(
                    itemView.image,
                    null,
                    BuildConfig.IMAGE_URL+result.logoPath.toString()
                )
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(
        LayoutInflater
            .from(p0.context).inflate(R.layout.item_network, p0, false)
    )


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bindView(data[p1])
    }

    fun addItem(movieNew: ArrayList<DetailTvModel.Network>) {
        movies.clear()
        movies.addAll(movieNew)
        notifyDataSetChanged()
    }

}