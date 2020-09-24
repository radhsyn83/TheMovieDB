package com.radhsyn83.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.ui.search.model.SearchModel
import com.radhsyn83.themoviedb.utils.Tools
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

//
// Created by Fathur Radhy 
// on May 2019-05-28.
//

interface SearchViewHolderInterface {
    fun bind(res: SearchModel.Result)
}

class SearchAdapter(
    val context: Context,
    val listener: Listener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_TV = 1
    val TYPE_MOVIE = 2
    val TYPE_PERSON = 3

    interface Listener {
        fun onItemClicked(data: SearchModel.Result)
    }

    var data = arrayListOf<SearchModel.Result>()

    open inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView),
        SearchViewHolderInterface {
        override fun bind(res: SearchModel.Result) {
            itemView.onClick { listener.onItemClicked(res) }
        }
    }

    inner class TvVH(itemView: View) : VH(itemView) {
        override fun bind(res: SearchModel.Result) {
            super.bind(res)

            if (res.posterPath == null) {
                itemView.visibility = View.GONE
            } else {

                itemView.tv_title.text = res.originalName.toString()
                itemView.tv_release_date.text = Tools.getDate("${res.firstAirDate} 00:00:00")
                itemView.tv_rating.apply {
                    if (res.voteAverage == "0") {
                        visibility = View.GONE
                    } else {
                        visibility = View.VISIBLE
                        text = res.voteAverage
                    }

                }
                if (res.posterPath != "")
                    Tools.displayImageFromUrl(
                        itemView.image,
                        null,
                        BuildConfig.IMAGE_URL + res.posterPath.toString()
                    )

            }
        }
    }

    inner class MovieVH(itemView: View) : VH(itemView) {
        override fun bind(res: SearchModel.Result) {
            super.bind(res)
            if (res.posterPath == null) {
                itemView.visibility = View.GONE
            } else {
                itemView.tv_title.text = res.originalTitle.toString()
                itemView.tv_release_date.text = Tools.getDate("${res.releaseDate} 00:00:00")
                itemView.tv_rating.apply {
                    if (res.voteAverage == "0") {
                        visibility = View.GONE
                    } else {
                        visibility = View.VISIBLE
                        text = res.voteAverage
                    }

                }
                if (res.posterPath != "")
                    Tools.displayImageFromUrl(
                        itemView.image,
                        null,
                        BuildConfig.IMAGE_URL + res.posterPath.toString()
                    )
            }
        }
    }

    inner class PersonVH(itemView: View) : VH(itemView) {
        override fun bind(res: SearchModel.Result) {
            super.bind(res)
            if (res.profilePath == null) {
                itemView.visibility = View.GONE
            } else {
                itemView.tv_title.text = res.name.toString()
                itemView.tv_release_date.text = res.knownForDepartment
                itemView.tv_rating.visibility = View.GONE
                if (res.profilePath != "")
                    Tools.displayImageFromUrl(
                        itemView.image,
                        null,
                        BuildConfig.IMAGE_URL+res.profilePath.toString()
                    )
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TV -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_search, parent, false)
                TvVH(v)
            }
            TYPE_MOVIE -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_search, parent, false)
                MovieVH(v)
            }
            TYPE_PERSON -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_search, parent, false)
                PersonVH(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_search, parent, false)
                TvVH(v)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        val vh = holder as SearchViewHolderInterface
        vh.bind(data[pos])
    }

    override fun getItemViewType(position: Int): Int {
        var status = 0
        when(data[position].mediaType) {
            "tv" -> status = TYPE_TV
            "movie" -> status = TYPE_MOVIE
            "person" -> status = TYPE_PERSON
        }

        return status
    }

    fun initItem(searchNew: ArrayList<SearchModel.Result>) {
        data.clear()
        data = searchNew
        this.notifyDataSetChanged()
    }

}