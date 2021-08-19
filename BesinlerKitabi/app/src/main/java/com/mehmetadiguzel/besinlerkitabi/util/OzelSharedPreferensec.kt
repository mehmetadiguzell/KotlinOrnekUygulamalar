package com.mehmetadiguzel.besinlerkitabi.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

class OzelSharedPreferensec {
    private val ZAMAN="zaman"

    companion object {
        private var sharedPreferensec :SharedPreferences?=null

        @Volatile private var instance : OzelSharedPreferensec?=null
        private var lock=Any()
        @InternalCoroutinesApi
        operator fun invoke(context: Context) : OzelSharedPreferensec = instance ?: synchronized(lock){
            instance ?: ozelSharedPreferensecYap(context).also {
                instance= it
            }
        }
        private fun ozelSharedPreferensecYap(context: Context) : OzelSharedPreferensec{
            sharedPreferensec=PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPreferensec()

        }
    }
    fun zamanıKaydet(zaman : Long){
        sharedPreferensec?.edit {
        putLong(ZAMAN,zaman)
        }
    }
    fun zamanıAl()= sharedPreferensec?.getLong(ZAMAN,0)
}