package com.example.workshop.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.workshop.home.data.PhotoRepository
import com.example.workshop.home.data.PhotoUpdater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PhotoRepository,
    val workManager: WorkManager
) : ViewModel() {

    //magic boxing
    val photosV2 = repository.photos.map {
        HomeUiStateV2.Content(photos = it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, HomeUiStateV2.Loading)

    private var _events = MutableSharedFlow<String>()
    val events: SharedFlow<String> = _events

    init {
        viewModelScope.launch {
            delay(500)
            _events.emit("Hallo")
        }

        workManager.enqueueUniquePeriodicWork(
            "updater", ExistingPeriodicWorkPolicy.KEEP, PeriodicWorkRequest
                .Builder(PhotoUpdater::class.java, 15, TimeUnit.MINUTES)
                .build()
        )
    }
}