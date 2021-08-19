package com.mehmetadiguzel.besinlerkitabi.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mehmetadiguzel.besinlerkitabi.model.Besin
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(Besin::class),version = 1)
abstract class BesinDatabase : RoomDatabase() {

    abstract fun besinDao() : BesinDAO

    //Singleton ->Tek bir obje oluştalan sınıflardır

    companion object{

        @Volatile private var instance :BesinDatabase?=null

        var lock=Any()

        @InternalCoroutinesApi
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOluştur(context).also {
                instance=it
            }
        }

        private fun databaseOluştur(context: Context)= Room.databaseBuilder(
                context.applicationContext,
                BesinDatabase::class.java,
                "besindatabase"
        ).build()
    }


}