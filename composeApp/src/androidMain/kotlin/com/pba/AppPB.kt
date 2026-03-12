package com.pba

import android.app.Application
import com.pba.di.appContext

class AppPB : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}