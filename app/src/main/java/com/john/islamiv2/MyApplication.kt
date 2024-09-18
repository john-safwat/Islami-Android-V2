package com.john.islamiv2

import android.app.Application
import com.john.islamiv2.Database.SurasDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SurasDatabase.initDatabase(this)
    }
}