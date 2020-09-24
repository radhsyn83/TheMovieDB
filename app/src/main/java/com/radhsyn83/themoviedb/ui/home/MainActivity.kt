package com.radhsyn83.themoviedb.ui.home

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.adapter.HomeTvPopularAdapter
import com.radhsyn83.themoviedb.adapter.MoviesAdapter
import com.radhsyn83.themoviedb.adapter.MoviesTrendingAdapter
import com.radhsyn83.themoviedb.ui.base.BaseActivity
import com.radhsyn83.themoviedb.ui.detail.DetailActivity
import com.radhsyn83.themoviedb.ui.home.model.HomeMovieModel
import com.radhsyn83.themoviedb.ui.home.model.HomeTvPopularModel
import com.radhsyn83.themoviedb.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_popular.*
import kotlinx.android.synthetic.main.main_trending.*
import kotlinx.android.synthetic.main.main_upcoming.rv_upcoming
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor
import java.util.ArrayList

class MainActivity : BaseActivity(), MoviesAdapter.Listener, MoviesTrendingAdapter.Listener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var trendingAdapter: MoviesTrendingAdapter
    private var trendingModel: ArrayList<HomeMovieModel.Result> = arrayListOf()

    override fun setLayoutResource(): Int =
        R.layout.activity_main

    override fun onViewReady() {

        initComponents()

        viewModel = ViewModelProvider(this, viewModelFactory { HomeViewModel(this) })
            .get(HomeViewModel::class.java)

        viewModel.popular.observe(this, Observer {
            updatePopular(it)
        })

        viewModel.upcoming.observe(this, Observer {
            updateUpcoming(it)
        })

        viewModel.trending.observe(this, Observer {
            updateTrending(it)
        })

    }

    private fun initComponents() {
        trendingRb.onCheckedChange { _, checkedId ->
            when(checkedId) {
                R.id.rbLeft -> {
                    rbLeft.textColor = ContextCompat.getColor(this@MainActivity, R.color.light_white)
                    rbRight.textColor = ContextCompat.getColor(this@MainActivity, R.color.light_orange)
                    viewModel.loadTranding(true)
                }
                R.id.rbRight -> {
                    rbRight.textColor = ContextCompat.getColor(this@MainActivity, R.color.light_white)
                    rbLeft.textColor = ContextCompat.getColor(this@MainActivity, R.color.light_orange)
                    viewModel.loadTranding(false)
                }
            }
        }

        //Trending
        trendingAdapter = MoviesTrendingAdapter(
            this, trendingModel,
            object : MoviesTrendingAdapter.Listener {
                override fun onItemClicked(data: HomeMovieModel.Result) {
                    startActivity<DetailActivity>("mediaType" to "tv", "id" to data.id)
                }
            }
        )
        rv_trending.apply {
            visibility = View.VISIBLE
            adapter = trendingAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            isNestedScrollingEnabled = true
        }

        et_search.apply {
            imeOptions = EditorInfo.IME_ACTION_SEARCH
            setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    et_search.clearFocus()

                    startActivity<SearchActivity>("key" to et_search.text.toString())
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }

    private fun updateTrending(res: ArrayList<HomeMovieModel.Result>?) {
        trendingAdapter.addItem(res ?: arrayListOf())
    }

    private fun updateUpcoming(res: ArrayList<HomeMovieModel.Result>?) {
        val moviesAdapter = MoviesAdapter(
            this, res ?: arrayListOf(),
            object : MoviesAdapter.Listener {
                override fun onItemClicked(data: HomeMovieModel.Result) {
                    startActivity<DetailActivity>("mediaType" to "movie", "id" to data.id)
                }
            }
        )
        rv_upcoming.apply {
            visibility = View.VISIBLE
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            isNestedScrollingEnabled = true
        }
    }

    private fun updatePopular(res: ArrayList<HomeTvPopularModel.Result>?) {
        val moviesAdapter = HomeTvPopularAdapter(
            this, res ?: arrayListOf(),
            object : HomeTvPopularAdapter.Listener {
                override fun onItemClicked(data: HomeTvPopularModel.Result) {
                    startActivity<DetailActivity>("mediaType" to "tv", "id" to data.id)
                }
            }
        )
        rv_popular.apply {
            visibility = View.VISIBLE
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            isNestedScrollingEnabled = true
        }
    }

    override fun onItemClicked(data: HomeMovieModel.Result) {
    }
}