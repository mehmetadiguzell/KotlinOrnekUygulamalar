package com.mehmetadiguzel.besinlerkitabi.viewmodel

import android.app.Application
import android.os.ParcelUuid
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehmetadiguzel.besinlerkitabi.model.Besin
import com.mehmetadiguzel.besinlerkitabi.service.BesinDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class BesinDetayiViewModel(application: Application) :BaseViewModel(application) {

    val besinLiveData =MutableLiveData<Besin>()

    @InternalCoroutinesApi
    fun roomVerisiniAl(uuid:Int){
        launch {
            val dao=BesinDatabase(getApplication()).besinDao()
            val besin=dao.getBesin(uuid)
            besinLiveData.value= besin
        }

    }
}