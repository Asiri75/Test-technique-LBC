package com.asiri.lbctest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.asiri.lbctest.model.Title

/**
 * Title data access object to simplify the database queries
 */
@Dao
interface TitleDao {

    //Get all the albums
    @Query("SELECT * FROM title")
    fun getAllTitles() : LiveData<List<Title>>

    //Get all the title for identified album
    @Query("SELECT * FROM title WHERE albumId = :albumId")
    fun getTitlesByAlbumId(albumId: Int) : LiveData<List<Title>>

    //Insert all titles: in case of conflict, update it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTitles(titles: List<Title>)
}