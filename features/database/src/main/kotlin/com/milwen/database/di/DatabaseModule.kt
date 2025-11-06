package com.milwen.database.di

import androidx.room.Room
import com.milwen.database.data.Config
import com.milwen.database.data.ScratchDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ScratchDatabase::class.java,
            Config.DATABASE_NAME,
        ).apply {
            addMigrations(
                // Future migrations
            )

            // Prevent failed migrations so database is not corrupted
            fallbackToDestructiveMigration(true)
        }
        .build()
    }

    // DAOs
    single { get<ScratchDatabase>().scratchCardDao }
}