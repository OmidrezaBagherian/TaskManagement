package com.omidrezabagherian.taskmanagement.ui.done

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omidrezabagherian.taskmanagement.data.TaskManagementRepository
import com.omidrezabagherian.taskmanagement.domian.models.TaskStatus
import com.omidrezabagherian.taskmanagement.domian.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {

    private val _doneList = MutableStateFlow<List<Task>>(emptyList())
    val doneList: StateFlow<List<Task>> = _doneList.asStateFlow()

    fun setList(taskStatus: TaskStatus) {
        viewModelScope.launch {
            repository.getTaskByStatus(taskStatus).collect { list ->
                _doneList.emit(list)
            }
        }
    }
}