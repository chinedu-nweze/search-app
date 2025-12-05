package com.search.app

import android.app.Application
import com.search.app.module.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration
import timber.log.Timber

@OptIn(KoinExperimentalAPI::class)
class MainApplication : Application(), KoinStartup {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    override fun onKoinStartup() = KoinConfiguration {
        androidLogger()
        androidContext(this@MainApplication)
        modules(appModules)
    }
}