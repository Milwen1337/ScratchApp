package com.milwen.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.milwen.database.data.enum.ScratchCardEntityStatus

@Entity(
    tableName = ScratchCardEntity.TABLE_NAME,
)
data class ScratchCardEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int = SINGLETON_ID,
    @ColumnInfo(name = "status") val status: ScratchCardEntityStatus,
    @ColumnInfo(name = "reveal_code") val revealCode: String? = null,
) {
    companion object {
        const val TABLE_NAME = "scratch_cards"
        const val SINGLETON_ID = 1
    }
}