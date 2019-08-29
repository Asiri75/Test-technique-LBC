package com.asiri.lbctest.albumslist

import androidx.lifecycle.*
import com.asiri.lbctest.database.AppDatabase
import com.asiri.lbctest.model.Album
import com.asiri.lbctest.model.Title
import timber.log.Timber

class AlbumsListViewModel : ViewModel() {

    private val albumsListLiveData = MutableLiveData<List<Album>>()

    //Function that initialize the titles observer and return the livedata to listen to it
    fun getAlbumsListLiveData(lifecycle: LifecycleOwner, appDatabase: AppDatabase): LiveData<List<Album>> {
        appDatabase.titleDao().getAllTitles().observe(lifecycle, Observer { titles ->
            if (titles != null)
                fillAlbumsWithTitles(titles)
        })
        return albumsListLiveData
    }

    private fun fillAlbumsWithTitles(titles: List<Title>) {
        //Creating albums list
        val albums = ArrayList<Album>()
        var albumId = -1
        for(title in titles){
            if(title.albumId > albumId) {
                val album = Album(title.albumId, 1, title.thumbnailUrl)
                albumId++
                albums.add(album)
            }else{
                albums.last().nbOfTitles++
            }
        }
        Timber.d("%d albums retieved from database", albums.size)
        albumsListLiveData.value = albums
    }


}