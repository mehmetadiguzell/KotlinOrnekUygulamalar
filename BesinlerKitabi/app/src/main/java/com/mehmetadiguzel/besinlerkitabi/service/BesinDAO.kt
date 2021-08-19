package com.mehmetadiguzel.besinlerkitabi.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mehmetadiguzel.besinlerkitabi.model.Besin

@Dao   //bu interfacesin dao olduğunu söyle
interface BesinDAO {
    //DATA ACCSES OBJECT
    //insert ->room,insert into işlemi
    //suspend ->coroutine scope
    //vararg birden fazla ve istediğimz kadar besin
    //list<long> ->nedeni id ler
    @Insert
    suspend fun insertAll(vararg besin: Besin):List<Long>

    @Query("SELECT * FROM besin")
    suspend fun getAllBesin():List<Besin>

    @Query("SELECT * FROM besin WHERE uuid =:besinId")
    suspend fun getBesin(besinId : Int):Besin

    @Query("DELETE FROM besin")
    suspend fun deleteAllBesin()
    

}