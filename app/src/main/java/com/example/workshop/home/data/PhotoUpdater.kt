package com.example.workshop.home.data

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workshop.home.db.DatabasePhoto
import com.example.workshop.home.db.WorkshopDatabase
import com.unsplashed.client.UnsplashedClient
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class PhotoUpdater @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val api: UnsplashedClient,
    private val db: WorkshopDatabase,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("Foo", "Updating Photos in the Background")

        try {

            val response = api.getPhotos().map {
                Photo(
                    user = it.user.name,
                    url = it.links.download.orEmpty()
                )
            }

            response.forEach { photo ->
                db.photos().insert(
                    DatabasePhoto(
                        url = photo.url,
                        user = photo.user
                    )
                )
            }
        } catch (e: Exception) {
            return Result.retry()
        }

        return Result.success()

    }
}