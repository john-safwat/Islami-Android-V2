package com.john.islamiv2.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.john.islamiv2.Models.Sura

@Dao
interface SurasDao {
    @Insert
    fun addSura(sura: Sura)
    @Update
    fun updateSura(sura: Sura)
    @Delete
    fun deleteSura(sura: Sura)
    @Query("SELECT * FROM suras ORDER BY updateTime DESC")
    fun getAllSuras(): MutableList<Sura>
}