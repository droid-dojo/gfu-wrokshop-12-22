package com.example.workshop.home.db.di

import android.content.Context
import androidx.room.Room
import com.example.workshop.home.db.WorkshopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun database(@ApplicationContext context: Context): WorkshopDatabase {

        return Room
            .databaseBuilder(context, WorkshopDatabase::class.java, "photos.db")
            .build()

    }
}