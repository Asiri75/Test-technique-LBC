package com.asiri.lbctest.titleslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.asiri.lbctest.database.AppDatabase
import com.asiri.lbctest.model.Title

class TitlesListViewModel: ViewModel() {

    //Function to get the livedata from the database
    fun getTitlesListLiveData(appDatabase: AppDatabase, albumId: Int): LiveData<List<Title>> {
        return appDatabase.titleDao().getTitlesByAlbumId(albumId)
    }

}