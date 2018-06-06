package com.android.zore3x.notlin

import android.app.Application
import android.arch.persistence.room.Room
import com.android.zore3x.notlin.data.AppDatabase

class App : Application() {

    private val DATABASE_NAME: String = "notLin.db"

    companion object {
        lateinit var appDatabase: AppDatabase
        private set
        get
    }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME)
                .build()
    }

}