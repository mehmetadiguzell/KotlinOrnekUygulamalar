package com.mehmetadiguzel.besinlerkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehmetadiguzel.besinlerkitabi.model.Besin
import com.mehmetadiguzel.besinlerkitabi.service.BesinAPIService
import com.mehmetadiguzel.besinlerkitabi.service.BesinDatabase
import com.mehmetadiguzel.besinlerkitabi.util.OzelSharedPreferensec
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class BesinListesiViewModel(application: Application) : BaseViewModel(application){
    val besinler=MutableLiveData<List<Besin>>()
    val besinHataMesaji=MutableLiveData<Boolean>()
    val besinYukleniyor=MutableLiveData<Boolean>()
    private var guncellemeZamani=10*60*1000*1000*1000L
    @InternalCoroutinesApi
    private val ozelSharedPreferensec=OzelSharedPreferensec(getApplication())

    private val besinApiService=BesinAPIService()
    private val disposable=CompositeDisposable()//kullan at

    @InternalCoroutinesApi
    fun refreshData(){
       /* val muz=Besin("Muz","100","10","5","1","www.test.com")
        val cilek=Besin("Çilek","100","10","5","1","www.test.com")
        val elma=Besin("Elma","100","10","5","1","www.test.com")

        val besinListesi = arrayListOf<Besin>(muz,cilek,elma)

        besinler.value=besinListesi
        besinHataMesaji.value=false
        besinYukleniyor.value=false

        */
        val kaydedilmeZamani=ozelSharedPreferensec.zamanıAl()
        if(kaydedilmeZamani != null && kaydedilmeZamani !=0L && System.nanoTime()-kaydedilmeZamani<guncellemeZamani){
            //sqlilite tan çek
            sqliteVeriCek()
        }else{
            verileriInternettenAl()
        }


    }
    @InternalCoroutinesApi
    fun refreshFromInternet(){
        verileriInternettenAl()
    }

    @InternalCoroutinesApi
    private fun sqliteVeriCek(){

        besinYukleniyor.value=true
        launch {
            val besinListesi=BesinDatabase(getApplication()).besinDao().getAllBesin()
            besinleriGoster(besinListesi)

        }

    }
    @InternalCoroutinesApi
    private fun verileriInternettenAl(){
        besinYukleniyor.value=true
        disposable.add(
                besinApiService.getData()
                        .subscribeOn(Schedulers.newThread())//arka planda çalışan tread
                        .observeOn(AndroidSchedulers.mainThread())//ana tread
                        .subscribeWith(object : DisposableSingleObserver<List<Besin>>(){
                            override fun onSuccess(t: List<Besin>) {
                                //başarılı olursa
                               sqliteSakla(t)
                            }

                            override fun onError(e: Throwable) {
                                //hata alrısak
                                besinHataMesaji.value=true
                                besinYukleniyor.value=false
                                e.printStackTrace()

                            }

                        })
        )

    }
    private fun besinleriGoster(besinlerListesi:List<Besin>){
        besinler.value=besinlerListesi
        besinHataMesaji.value=false
        besinYukleniyor.value=false
    }
    @InternalCoroutinesApi
    private fun sqliteSakla(besinListesi : List<Besin>){
    launch {
        val dao=BesinDatabase(getApplication()).besinDao()
        dao.deleteAllBesin()
        val uuidIdListesi=dao.insertAll(*besinListesi.toTypedArray())
        var i=0
        while (i<besinListesi.size){
            besinListesi[i].uuid=uuidIdListesi[i].toInt()
            i++
        }
        besinleriGoster(besinListesi)
    }
        ozelSharedPreferensec.zamanıKaydet(System.nanoTime())
    }
}