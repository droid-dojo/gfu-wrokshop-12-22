package com.example.workshop.home.data

import android.util.Log
import com.example.workshop.home.db.WorkshopDatabase
import com.unsplashed.client.UnsplashedClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

import com.example.workshop.home.data.Photo as DomainPhoto
import com.example.workshop.home.db.DatabasePhoto as DatabasePhoto

@Singleton
class PhotoRepositoryImpl @Inject constructor(
    private val api: UnsplashedClient,
    private val db: WorkshopDatabase,
) : PhotoRepository {


    private val repositoryScope = CoroutineScope(Dispatchers.IO)


    override val photos: Flow<List<DomainPhoto>> = db.photos().observePhotos().map {
        it.map { db ->
            DomainPhoto(user = db.user, url = db.url)
        }
    }

    init {
        repositoryScope.launch {
            Log.d("Foo", "Fetching from network")
            val response = api.getPhotos().map {
                DomainPhoto(
                    user = it.user.name,
                    url = it.links.download.orEmpty()
                )
            }

            response.forEach {
                db.photos().insert(
                    DatabasePhoto(
                        url = it.url,
                        user = it.user
                    )
                )
            }
        }
    }

}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(repo: PhotoRepositoryImpl): PhotoRepository = repo

}