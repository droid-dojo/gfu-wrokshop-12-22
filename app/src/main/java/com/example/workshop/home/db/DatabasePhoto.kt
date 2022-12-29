package com.example.workshop.home.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photo")
data class DatabasePhoto(
    @PrimaryKey val url: String,
    @ColumnInfo(defaultValue = "Database") val user: String
)
