package com.omidrezabagherian.karaapplication.domian.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task_db")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val taskStatus: TaskStatus
) : Serializable
