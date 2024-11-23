package com.example.gskotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SugestaoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String
)