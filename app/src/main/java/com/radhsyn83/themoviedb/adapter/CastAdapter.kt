package com.radhsyn83.themoviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.ui.detail.model.Credits
import com.radhsyn83.themoviedb.ui.detail.model.DetailCastModel
import com.radhsyn83.themoviedb.ui.detail.model.DetailTvModel
import com.radhsyn83.themoviedb.utils.Tools
import kotlinx.android.synthetic.main.item_cast.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

//
// Created by Fathur Radhy 
// on May 2019-05-28.
//

class CastAdapter(
    val context: Context,
    val data: ArrayList<Credits.Cast>,
    val listener: Listener?
) : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    val movies: ArrayList<Credits.Cast> = data

    interface Listener {
        fun onItemClicked(data: Credits.Cast)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(result: Credits.Cast) {
            itemView.tv_name.text = result.name
            itemView.tv_job.text = result.character

            if (result.profilePath != "")
                Tools.displayImageFromUrl(
                    itemView.image,
                    null,
                    BuildConfig.IMAGE_URL+result.profilePath.toString()
                )
            itemView.onClick { listener?.onItemClicked(result) }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = ViewHolder(
        LayoutInflater
            .from(p0.context).inflate(R.layout.item_cast, p0, false)
    )


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bindView(data[p1])
    }

    fun addItem(movieNew: ArrayList<Credits.Cast>) {
        movies.clear()
        movies.addAll(movieNew)
        notifyItemInserted(itemCount)
    }

}