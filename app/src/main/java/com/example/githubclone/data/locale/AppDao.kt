package com.example.githubclone.data.locale

import androidx.room.*
import com.example.githubclone.data.models.HistoryData
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertText(historyData: HistoryData)

    @Query("DELETE FROM my_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM my_table")
    suspend fun getAllText():List<HistoryData>
}