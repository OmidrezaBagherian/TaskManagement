package com.omidrezabagherian.karaapplication.data

import com.omidrezabagherian.karaapplication.data.local.LocalDataSource
import com.omidrezabagherian.karaapplication.domian.models.TaskStatus
import com.omidrezabagherian.karaapplication.domian.models.Task
import kotlinx.coroutines.flow.Flow

class TaskManagementRepository(private val localDataSource: LocalDataSource) {

    fun getAllTask(): Flow<List<Task>> = localDataSource.getAllTask()

    fun getTaskByStatus(taskStatus: TaskStatus): Flow<List<Task>> =
        localDataSource.getTaskByStatus(taskStatus)

    fun getTaskById(id: Int): Flow<Task> = localDataSource.getTaskById(id)

    suspend fun insertTask(task: Task) {
        localDataSource.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        localDataSource.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        localDataSource.updateTask(task)
    }
}