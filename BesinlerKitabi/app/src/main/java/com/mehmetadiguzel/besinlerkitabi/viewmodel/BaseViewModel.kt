package com.mehmetadiguzel.besinlerkitabi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class BaseViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {
    private val job= Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main //arkaplanda ki işi bitir maine dön

    override fun onCleared() {
        super.onCleared() //view model ile ilgi işlemler bittikten sonra işlemleri bitir.
        job.cancel()
    }
}