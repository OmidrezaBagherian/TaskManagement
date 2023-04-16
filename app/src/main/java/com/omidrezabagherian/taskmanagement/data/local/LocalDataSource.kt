package com.omidrezabagherian.taskmanagement.data.local

import com.omidrezabagherian.taskmanagement.domian.models.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val taskManagementDao: TaskManagementDao) {

    fun getAllTask(): Flow<List<Task>> = taskManagementDao.getAllTask()

    suspend fun insertTask(task: Task) {
        taskManagementDao.insertTask(task)
    }
}