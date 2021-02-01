package com.soufianekre.pokebox

import android.app.Application
import timber.log.Timber

class PokeboxApp : Application(){


    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }

    companion object{
        var instance : PokeboxApp? = null
        fun getInstance() : Application{
            return instance!!
        }

    }
}