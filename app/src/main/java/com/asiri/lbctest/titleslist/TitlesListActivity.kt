package com.asiri.lbctest.titleslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.asiri.lbctest.App
import com.asiri.lbctest.R
import com.asiri.lbctest.model.Title
import kotlinx.android.synthetic.main.activity_titles_list.*
import timber.log.Timber
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL


class TitlesListActivity : AppCompatActivity(), TitlesListAdapter.TitlesListAdapterListener {

    companion object {
        const val ALBUM_ID_EXTRA = "ALBUM_ID_EXTRA"
    }

    private lateinit var titleListAdapter: TitlesListAdapter
    private lateinit var titlesListViewModel: TitlesListViewModel

    private val titlesList = ArrayList<Title>()
    private var albumId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_titles_list)

        if (intent != null) {
            albumId = intent.getIntExtra(ALBUM_ID_EXTRA, 0)
        } else finish()

        titlesListViewModel = ViewModelProviders.of(this).get(TitlesListViewModel::class.java)
        initDataAndRecycler()
    }

    private fun initDataAndRecycler() {
        Timber.d("Initialisation of recycler")
        titleListAdapter = TitlesListAdapter(titlesList, this)
        titles_list.adapter = titleListAdapter
        titlesListViewModel.getTitlesListLiveData(App.db, albumId).observe(this, Observer { titles ->
            titlesList.clear()
            titlesList.addAll(titles)
            titles_progress_bar.visibility = View.GONE
            titleListAdapter.notifyDataSetChanged()
        })
        titles_progress_bar.visibility = View.VISIBLE
    }

    override fun onTitleSelected(title: Title) {

    }
}
