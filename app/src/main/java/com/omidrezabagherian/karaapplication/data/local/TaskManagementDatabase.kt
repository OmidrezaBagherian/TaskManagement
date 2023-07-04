package com.omidrezabagherian.karaapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omidrezabagherian.karaapplication.domian.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TaskManagementDatabase : RoomDatabase() {

    abstract fun taskManagementDao(): TaskManagementDao
}