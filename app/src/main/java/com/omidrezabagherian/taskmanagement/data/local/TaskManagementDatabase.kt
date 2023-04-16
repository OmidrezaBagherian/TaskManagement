package com.omidrezabagherian.taskmanagement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omidrezabagherian.taskmanagement.domian.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TaskManagementDatabase : RoomDatabase() {

    abstract fun taskManagementDao(): TaskManagementDao
}