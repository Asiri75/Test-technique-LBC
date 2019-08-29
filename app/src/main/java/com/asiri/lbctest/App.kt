package com.asiri.lbctest

import android.app.Application
import androidx.room.Room
import com.asiri.lbctest.database.AppDatabase
import com.asiri.lbctest.database.DATABASE_NAME
import com.asiri.lbctest.repository.TitleRepository
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
        lateinit var titleRepository: TitleRepository
    }

    override fun onCreate() {
        super.onCreate()

        //We initialize the logging library
        Timber.plant(Timber.DebugTree())

        //Attach the database reference to the app for global access
        db = Room.databaseBuilder(this,
            AppDatabase::class.java, DATABASE_NAME)
            .build()

        //Same for the album repository
        titleRepository = TitleRepository()
    }

}