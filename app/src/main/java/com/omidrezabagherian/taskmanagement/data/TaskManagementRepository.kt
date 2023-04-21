package com.omidrezabagherian.taskmanagement.data

import com.omidrezabagherian.taskmanagement.data.local.LocalDataSource
import com.omidrezabagherian.taskmanagement.domian.models.TaskStatus
import com.omidrezabagherian.taskmanagement.domian.models.Task
import kotlinx.coroutines.flow.Flow

class TaskManagementRepository(private val localDataSource: LocalDataSource) {

    fun getAllTask(): Flow<List<Task>> = localDataSource.getAllTask()

    fun getTaskByStatus(taskStatus: TaskStatus): Flow<List<Task>> =
        localDataSource.getTaskByStatus(taskStatus)

    fun getTaskById(id: Int): Flow<Task> = localDataSource.getTaskById(id)

    suspend fun insertTask(task: Task) {
        localDataSource.insertTask(task)
    }
}