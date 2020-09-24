package com.radhsyn83.themoviedb.ui.detail

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.adapter.CastAdapter
import com.radhsyn83.themoviedb.adapter.TvNetworkAdapter
import com.radhsyn83.themoviedb.adapter.TvRecommendationAdapter
import com.radhsyn83.themoviedb.ui.base.BaseActivity
import com.radhsyn83.themoviedb.ui.detail.model.Credits
import com.radhsyn83.themoviedb.ui.detail.model.DetailTvModel
import com.radhsyn83.themoviedb.ui.detail.model.Recommendations
import com.radhsyn83.themoviedb.ui.person.PersonActivity
import com.radhsyn83.themoviedb.ui.youtube.YoutubeActivity
import com.radhsyn83.themoviedb.utils.Tools
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_recomendation.*
import kotlinx.android.synthetic.main.item_youtube.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DetailActivity : BaseActivity() {

    private lateinit var viewModel: DetailViewModel

    private var id = 0
    private lateinit var mediaType: String

    override fun setLayoutResource(): Int = R.layout.activity_detail

    override fun onViewReady() {

        Tools.customStatusBar(this, true, lightStatusBar = true)

        viewModel = ViewModelProvider(this, viewModelFactory { DetailViewModel(this) })
            .get(DetailViewModel::class.java)

        viewModel.tv.observe(this, Observer {
            updateForm(it)
        })

        viewModel.movie.observe(this, Observer {
            updateForm(it)
        })

        initComponents()

    }

    private fun updateTvCast(res: ArrayList<Credits.Cast>?) {
        val castAdapter = CastAdapter(
            this, res ?: arrayListOf(),
            object : CastAdapter.Listener {
                override fun onItemClicked(data: Credits.Cast) {
                    startActivity<PersonActivity>("id" to data.id)
                }
            }
        )
        rv_cast.apply {
            visibility = View.VISIBLE
            adapter = castAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            isNestedScrollingEnabled = true
        }
    }

    private fun updateRecommendation(res: ArrayList<Recommendations.Result>?) {
        val recomAdapter = TvRecommendationAdapter(
            this, res ?: arrayListOf(),
            object : TvRecommendationAdapter.Listener {
                override fun onItemClicked(data: Recommendations.Result) {
                    startActivity<DetailActivity>("mediaType" to "tv", "id" to data.id)
                }
            }
        )
        rv_recommendation.apply {
            visibility = View.VISIBLE
            adapter = recomAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            isNestedScrollingEnabled = true
        }
    }

    private fun initComponents() {
        intent?.getStringExtra("mediaType")?.let {
            mediaType = it
        }
        intent?.getIntExtra("id", 0)?.let {
            id = it
        }

        if (mediaType == "tv") {
            viewModel.loadTv(id)
        } else {
            viewModel.loadMovies(id)
        }
    }

    private fun updateForm(res: DetailTvModel?) {
        res?.let { r ->
            updateTvCast(res.credits?.cast)
            updateRecommendation(res.recommendations?.results)
            updateNetwork(res.networks)

            Tools.displayImageFromUrl(
                backdrop_img,
                null,
                BuildConfig.IMAGE_URL+r.backdropPath
            )

            val date = if (mediaType == "tv") res.firstAirDate ?: "01/01/2001" else res.releaseDate ?: "01/01/2001"
            val status = if (mediaType == "tv") if (res.inProduction!!) getString(R.string.on_going) else getString(R.string.release) else res.status
            val name = if (mediaType == "tv") res.originalName else res.originalTitle
            val episodeRunTime = res.episodeRunTime ?: arrayListOf()
            var ep = 0
            if (episodeRunTime.size > 0)
                ep = episodeRunTime[0]
            val lengthMinutes = if (mediaType == "tv") (ep) else (res.runtime ?: 0)

            tv_title.text = name
            tv_rating.text = res.voteAverage.toString()
            tv_country_release.text = getString(R.string.format_multy_string, res.originalLanguage, Tools.getDate("${date} 00:00:00"))
            tv_status.text = status

            tv_genres_lenght.text = getString(R.string.format_multy_string, convertMinutesToMinutesHour(lengthMinutes), convertGenres(res.genres))
            tv_desc.text = res.overview

            //trailer
            val video = res.videos?.results ?: arrayListOf()

            if (video.size > 0) {
                val thumb = "https://img.youtube.com/vi/${video[0]?.key}/maxresdefault.jpg"
                Log.d("IMAGE", thumb)
                if(thumb != "")
                    Tools.displayImageFromUrl(image_yt, null, thumb)

                image_yt.onClick {
                    startActivity<YoutubeActivity>("youtubeId" to video[0]?.key)
                }
            } else {
                ll_trailer.visibility = View.GONE
            }

            tv_homepage.apply {
                onClick {
                    val link = res.homepage
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    startActivity(browserIntent)
                }
                paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG

                if (res.homepage == "" || res.homepage == null)
                    visibility = View.GONE
            }
        }
    }

    private fun updateNetwork(res: ArrayList<DetailTvModel.Network>?) {
        val networkAdapter = TvNetworkAdapter(
            this, res ?: arrayListOf()
        )
        rv_network.apply {
            visibility = View.VISIBLE
            adapter = networkAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            isNestedScrollingEnabled = true
        }
    }

    private fun convertMinutesToMinutesHour(lengthMinutes: Int) : String {
        var lengthString = ""
        val hours: Int = lengthMinutes / 60
        val minutes: Int = lengthMinutes % 60
        lengthString = if (hours == 0)
            minutes.toString() + "min"
        else
            getString(R.string.time_format_length, hours.toString(), minutes.toString())

        return lengthString
    }

    private fun convertGenres(genres: ArrayList<DetailTvModel.Genre>?) : String{
        var genre = ""
        genres?.forEachIndexed { i, u ->
            genre += if (i == 0)
                u.name
            else
                ", " + u.name
        }

        return genre
    }
}