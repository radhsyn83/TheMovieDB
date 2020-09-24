package com.radhsyn83.themoviedb.ui.person

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.adapter.KnownForAdapter
import com.radhsyn83.themoviedb.ui.base.BaseActivity
import com.radhsyn83.themoviedb.ui.detail.DetailActivity
import com.radhsyn83.themoviedb.ui.person.model.PersonModel
import com.radhsyn83.themoviedb.utils.Tools
import kotlinx.android.synthetic.main.activity_person.*
import kotlinx.android.synthetic.main.activity_person.tv_desc
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.ArrayList

class PersonActivity : BaseActivity() {

    private lateinit var viewModel: PersonViewModel

    private var id = 0

    override fun setLayoutResource(): Int = R.layout.activity_person

    override fun onViewReady() {
        viewModel = ViewModelProvider(this, viewModelFactory { PersonViewModel(this) })
            .get(PersonViewModel::class.java)

        viewModel.person.observe(this, Observer {
            updateForm(it)
        })

        initComponents()
    }

    private fun initComponents() {
        intent?.getIntExtra("id", 0)?.let {
            id = it
        }

        viewModel.load(id)
    }

    private fun updateForm(res: PersonModel?) {
        res?.let { r ->
            Tools.displayImageFromUrl(
                image,
                null,
                BuildConfig.IMAGE_URL+r.profilePath
            )

            val knownAs = r.alsoKnownAs ?: arrayListOf()
            if (knownAs.size > 0)
                tv_also_known.text = r.alsoKnownAs?.get(0) ?: "-"

            tv_name.text = r.name
            tv_place_birth.text = r.placeOfBirth
            tv_birth.text = Tools.getDate("${r.birthday} 00:00:00")
            tv_desc.text = r.biography
            tv_known.text = r.knownForDepartment

            updateKnowFor(res.combinedCredits?.cast)
        }
    }

    private fun updateKnowFor(res: ArrayList<PersonModel.CombinedCredits.Cast>?) {
        val knownForAdapter = KnownForAdapter(
            this, res ?: arrayListOf(),
            object : KnownForAdapter.Listener {
                override fun onItemClicked(data: PersonModel.CombinedCredits.Cast) {
                    startActivity<DetailActivity>("mediaType" to data.mediaType, "id" to data.id)
                }
            }
        )
        rv_known.apply {
            visibility = View.VISIBLE
            adapter = knownForAdapter
            layoutManager = LinearLayoutManager(this@PersonActivity)
            isNestedScrollingEnabled = true
        }
    }
}