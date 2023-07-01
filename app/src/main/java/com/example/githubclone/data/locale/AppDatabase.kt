package com.example.githubclone.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubclone.data.models.HistoryData

@Database(entities = [HistoryData::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun appDao():AppDao
}