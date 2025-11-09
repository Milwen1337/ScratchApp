package com.milwen.database.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.milwen.database.data.entity.ScratchCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScratchCardDao : BaseDao<ScratchCardEntity> {

    @Query("SELECT * FROM ${ScratchCardEntity.TABLE_NAME} WHERE id = :id LIMIT 1")
    suspend fun get(id: Int = ScratchCardEntity.SINGLETON_ID): ScratchCardEntity?

    @Query("SELECT * FROM ${ScratchCardEntity.TABLE_NAME} WHERE id = :id LIMIT 1")
    fun observe(id: Int = ScratchCardEntity.SINGLETON_ID): Flow<ScratchCardEntity?>

    @Query("DELETE FROM ${ScratchCardEntity.TABLE_NAME}")
    suspend fun clear()

}