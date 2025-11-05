package com.milwen.scratch.presentation

import android.app.Application
import com.milwen.database.di.databaseModule
import com.milwen.restapi.di.restModule
import com.milwen.scratch.di.mainModule
import com.milwen.scratch.di.scratchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    mainModule,
                    restModule,
                    scratchModule,
                    databaseModule,
                )
            )
        }

    }
}