package com.omidrezabagherian.taskmanagement.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omidrezabagherian.taskmanagement.domian.models.TaskStatus
import com.omidrezabagherian.taskmanagement.domian.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskManagementDao {
    @Query("SELECT * FROM task_db")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM task_db WHERE statusTask == :statusTask")
    fun getTaskByStatus(taskStatus: TaskStatus): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)
}