package com.radhsyn83.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.ui.home.model.HomeMovieModel
import com.radhsyn83.themoviedb.utils.Tools
import kotlinx.android.synthetic.main.item_movie_trending.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

//
// Created by Fathur Radhy 
// on May 2019-05-28.
//

class MoviesTrendingAdapter(
    val context: Context,
    val data: ArrayList<HomeMovieModel.Result>,
    val listener: Listener
) : RecyclerView.Adapter<MoviesTrendingAdapter.ViewHolder>() {

    val movies: ArrayList<HomeMovieModel.Result> = data

    interface Listener {
        fun onItemClicked(data: HomeMovieModel.Result)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(result: HomeMovieModel.Result) {
            itemView.tv_title.apply {
                text = if (result.originalTitle !== null)
                    result.originalTitle
                else
                    result.name
            }

            itemView.tv_desc.text = result.overview

            itemView.tv_release_date.apply {
                if (result.releaseDate !== null) {
                    text = Tools.getDate("${result.releaseDate} 00:00:00")
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }

            itemView.tv_rating.apply {
                if (result.voteAverage == "0") {
                    visibility = View.GONE
                } else {
                    visibility = View.VISIBLE
                    text = result.voteAverage
                }

            }

            if (result.posterPath != "")
                Tools.displayImageFromUrl(
                    itemView.image,
                    null,
                    BuildConfig.IMAGE_URL+result.posterPath.toString()
                )
            itemView.onClick { listener.onItemClicked(result) }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(
        LayoutInflater
            .from(p0.context).inflate(R.layout.item_movie_trending, p0, false)
    )


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bindView(data[p1])
    }

    fun addItem(movieNew: ArrayList<HomeMovieModel.Result>) {
        movies.clear()
        movies.addAll(movieNew)
        notifyDataSetChanged()
    }

}