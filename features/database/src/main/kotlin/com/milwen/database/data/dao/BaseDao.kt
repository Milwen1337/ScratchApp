package com.milwen.database.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<Entity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: Entity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(items: List<Entity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnoringExisting(item: Entity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnoringExisting(items: List<Entity>): List<Long>

    @Update
    suspend fun update(item: Entity)

    @Update
    suspend fun update(items: List<Entity>)

    @Delete
    suspend fun delete(item: Entity)
}