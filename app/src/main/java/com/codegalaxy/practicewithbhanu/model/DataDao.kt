package com.codegalaxy.practicewithbhanu.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: DataEntity): Long

    @Query("SELECT * FROM data_table WHERE name = :name AND year = :year")
    suspend fun findDataByNameAndYear(name: String, year: String): DataEntity?

    @Query("SELECT * FROM data_table")
    suspend fun getAllData(): List<DataEntity>
}
