package com.asiri.lbctest.albumslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.asiri.lbctest.App
import com.asiri.lbctest.R
import com.asiri.lbctest.model.Album
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), AlbumsListAdapter.AlbumsListAdapterListener  {

    private lateinit var albumsListViewModel: AlbumsListViewModel
    private var albumsList = ArrayList<Album>()
    private lateinit var albumListAdapter: AlbumsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.titleRepository.syncTitleNow()
        albumsListViewModel = ViewModelProviders.of(this).get(AlbumsListViewModel::class.java)
        Timber.d("Initialisation of recycler")
        initDataAndRecycler()
    }

    private fun initDataAndRecycler() {
        Timber.d("Initialisation of recycler")
        albumListAdapter = AlbumsListAdapter(albumsList, this)
        albums_list.adapter = albumListAdapter
        albumsListViewModel.getAlbumsListLiveData(this, App.db).observe(this, Observer { albums ->
            albumsList.clear()
            albumsList.addAll(albums)
            albums_progress_bar.visibility = View.GONE
            albumListAdapter.notifyDataSetChanged()
        })
        albums_progress_bar.visibility = View.VISIBLE
    }

    override fun onAlbumSelected(album: Album) {
        Timber.d("Album %d selected", album.id)
    }
}
