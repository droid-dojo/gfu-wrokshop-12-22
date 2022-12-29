package com.example.workshop.home.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DatabasePhoto::class],
    version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class WorkshopDatabase : RoomDatabase() {
    abstract fun photos() : PhotoDao
}