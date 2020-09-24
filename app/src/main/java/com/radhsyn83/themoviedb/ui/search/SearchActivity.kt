package com.radhsyn83.themoviedb.ui.search

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.adapter.CastAdapter
import com.radhsyn83.themoviedb.adapter.SearchAdapter
import com.radhsyn83.themoviedb.ui.base.BaseActivity
import com.radhsyn83.themoviedb.ui.detail.DetailActivity
import com.radhsyn83.themoviedb.ui.detail.DetailViewModel
import com.radhsyn83.themoviedb.ui.detail.model.Credits
import com.radhsyn83.themoviedb.ui.person.PersonActivity
import com.radhsyn83.themoviedb.ui.search.model.SearchModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.rv_cast
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class SearchActivity : BaseActivity() {
    private lateinit var viewModel: SearchViewModel

    override fun setLayoutResource(): Int = R.layout.activity_search

    override fun onViewReady() {

        viewModel = ViewModelProvider(this, viewModelFactory { SearchViewModel(this) })
            .get(SearchViewModel::class.java)

        viewModel.search.observe(this, Observer {
            updateRecyclerView(it)
        })

        initComponents()
    }

    private fun initComponents() {
        intent?.getStringExtra("key")?.let {
            viewModel.loadSearch(it)
            tv_keyword.text = it
        }
    }

    private fun updateRecyclerView(res: ArrayList<SearchModel.Result>?) {
        val searchAdapter = SearchAdapter(
            this,
            object : SearchAdapter.Listener {
                override fun onItemClicked(data: SearchModel.Result) {
                    if (data.mediaType == "person") {
                        startActivity<PersonActivity>("mediaType" to data.mediaType, "id" to data.id)
                    } else {
                        startActivity<DetailActivity>("mediaType" to data.mediaType, "id" to data.id)
                    }
                }
            }
        )

        rv_item.apply {
            visibility = View.VISIBLE
            adapter = searchAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            isNestedScrollingEnabled = true
        }

        val data = (res ?: arrayListOf()).iterator()
        val newData: ArrayList<SearchModel.Result> = arrayListOf()

        while(data.hasNext()){
            val item = data.next()
            when(item.mediaType) {
                "tv" -> if (item.posterPath != null) newData.add(item)
                "movie" -> if (item.posterPath != null) newData.add(item)
                "person" -> if (item.profilePath != null) newData.add(item)
            }
        }

        searchAdapter.initItem(newData)
    }

}