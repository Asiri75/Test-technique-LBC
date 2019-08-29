package com.asiri.lbctest

import android.app.Application
import androidx.room.Room
import com.asiri.lbctest.database.AppDatabase
import com.asiri.lbctest.database.DATABASE_NAME

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        //Attach the database reference to the app for global access
        db = Room.databaseBuilder(this,
            AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

}