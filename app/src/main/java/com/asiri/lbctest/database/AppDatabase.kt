package com.asiri.lbctest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asiri.lbctest.model.Title

const val DATABASE_NAME = "album_store"

/**
 * Internal database for data persistence
 */
@Database(entities = [Title::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun titleDao() : TitleDao
}