package com.example.workshop.home.data


import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    val photos: Flow<List<Photo>>

}