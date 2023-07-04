package com.omidrezabagherian.karaapplication.data.local

import com.omidrezabagherian.karaapplication.domian.models.TaskStatus
import com.omidrezabagherian.karaapplication.domian.models.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val taskManagementDao: TaskManagementDao) {

    fun getAllTask(): Flow<List<Task>> = taskManagementDao.getAllTask()

    fun getTaskByStatus(taskStatus: TaskStatus): Flow<List<Task>> =
        taskManagementDao.getTaskByStatus(taskStatus)

    fun getTaskById(id: Int): Flow<Task> = taskManagementDao.getTaskById(id)

    suspend fun insertTask(task: Task) {
        taskManagementDao.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskManagementDao.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskManagementDao.updateTask(task)
    }
}