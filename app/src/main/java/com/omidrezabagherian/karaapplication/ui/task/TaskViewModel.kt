package com.omidrezabagherian.karaapplication.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omidrezabagherian.karaapplication.data.TaskManagementRepository
import com.omidrezabagherian.karaapplication.domian.models.TaskStatus
import com.omidrezabagherian.karaapplication.domian.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: StateFlow<List<Task>> = _taskList.asStateFlow()

    fun setList(taskStatus: TaskStatus) {
        viewModelScope.launch {
            repository.getTaskByStatus(taskStatus).collect { list ->
                _taskList.emit(list)
            }
        }
    }
}