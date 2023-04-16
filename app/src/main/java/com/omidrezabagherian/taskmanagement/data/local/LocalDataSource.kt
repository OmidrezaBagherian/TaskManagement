package com.omidrezabagherian.taskmanagement.data.local

import com.omidrezabagherian.taskmanagement.domian.models.StatusTask
import com.omidrezabagherian.taskmanagement.domian.models.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val taskManagementDao: TaskManagementDao) {

    fun getAllTask(): Flow<List<Task>> = taskManagementDao.getAllTask()

    fun getTaskByStatus(statusTask: StatusTask): Flow<List<Task>> =
        taskManagementDao.getTaskByStatus(statusTask)

    suspend fun insertTask(task: Task) {
        taskManagementDao.insertTask(task)
    }
}