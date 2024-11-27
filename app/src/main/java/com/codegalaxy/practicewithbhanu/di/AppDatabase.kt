package com.codegalaxy.practicewithbhanu.di

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.codegalaxy.practicewithbhanu.model.DataDao
import com.codegalaxy.practicewithbhanu.model.DataEntity

@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}
