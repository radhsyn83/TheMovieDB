package com.radhsyn83.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.ui.detail.model.DetailTvModel
import com.radhsyn83.themoviedb.ui.detail.model.Recommendations
import com.radhsyn83.themoviedb.utils.Tools
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

class TvRecommendationAdapter(
    val context: Context,
    val data: ArrayList<Recommendations.Result>,
    val listener: Listener
) : RecyclerView.Adapter<TvRecommendationAdapter.ViewHolder>() {

    val movies: ArrayList<Recommendations.Result> = data

    interface Listener {
        fun onItemClicked(data: Recommendations.Result)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(result: Recommendations.Result) {
            itemView.tv_title.text =  result.name

            itemView.tv_desc.text = result.overview

            itemView.tv_release_date.apply {
                if (result.firstAirDate !== null) {
                    text = Tools.getDate("${result.firstAirDate} 00:00:00")
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

    fun addItem(movieNew: ArrayList<Recommendations.Result>) {
        movies.clear()
        movies.addAll(movieNew)
        notifyDataSetChanged()
    }

}