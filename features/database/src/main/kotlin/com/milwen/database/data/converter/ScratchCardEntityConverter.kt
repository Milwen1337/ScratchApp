package com.milwen.database.data.converter

import androidx.room.TypeConverter
import com.milwen.database.data.enum.ScratchCardEntityStatus

class ScratchCardEntityConverter {

    @TypeConverter
    fun fromStatus(value: ScratchCardEntityStatus): Int = value.ordinal

    @TypeConverter
    fun toStatus(value: Int): ScratchCardEntityStatus =
        ScratchCardEntityStatus.entries[value]

}