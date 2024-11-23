package com.example.gskotlin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gskotlin.model.SugestaoModel

@Database(entities = [SugestaoModel::class], version = 2)
abstract class SugestaoDataBase : RoomDatabase() {
    abstract fun sugestaoDao(): SugestaoDao
}