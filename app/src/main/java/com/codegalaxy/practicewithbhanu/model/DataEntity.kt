package com.codegalaxy.practicewithbhanu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val year: String,
    val price: String,
    val cpu: String,
    val hardDisk: String,
    //val ram: String? = null
)

