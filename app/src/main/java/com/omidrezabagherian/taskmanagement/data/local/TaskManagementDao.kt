package com.omidrezabagherian.taskmanagement.data.local

import androidx.room.*
import com.omidrezabagherian.taskmanagement.domian.models.TaskStatus
import com.omidrezabagherian.taskmanagement.domian.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskManagementDao {
    @Query("SELECT * FROM task_db")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM task_db WHERE taskStatus == :taskStatus")
    fun getTaskByStatus(taskStatus: TaskStatus): Flow<List<Task>>

    @Query("SELECT * FROM task_db WHERE id == :id")
    fun getTaskById(id: Int): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}