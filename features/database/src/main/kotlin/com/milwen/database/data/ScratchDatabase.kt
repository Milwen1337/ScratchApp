package com.milwen.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.milwen.database.data.converter.ScratchCardEntityConverter
import com.milwen.database.data.dao.ScratchCardDao
import com.milwen.database.data.entity.ScratchCardEntity

@Database(
    version = Config.DATABASE_VERSION,
    exportSchema = false,
    entities = [
        ScratchCardEntity::class,
    ],
)

@TypeConverters(
    ScratchCardEntityConverter::class,
)

abstract class ScratchDatabase : RoomDatabase() {

    abstract val scratchCardDao: ScratchCardDao

}