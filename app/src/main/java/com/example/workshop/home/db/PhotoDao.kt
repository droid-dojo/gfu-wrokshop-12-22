package com.example.workshop.home.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: DatabasePhoto)

    @Query("SELECT * FROM Photo")
    suspend fun getAllPhotos(): List<DatabasePhoto>

    @Query("SELECT * FROM Photo")
    fun observePhotos(): Flow<List<DatabasePhoto>>

}