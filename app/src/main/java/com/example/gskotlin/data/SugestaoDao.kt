package com.example.gskotlin.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gskotlin.model.SugestaoModel

@Dao
interface SugestaoDao {
    @Query("SELECT * FROM SugestaoModel")
    fun getAll(): LiveData<List<SugestaoModel>>
    @Insert
    fun insert(sugestao: SugestaoModel)
    @Delete
    fun delete(sugestao: SugestaoModel)
}