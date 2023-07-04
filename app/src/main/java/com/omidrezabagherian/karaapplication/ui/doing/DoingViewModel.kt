package com.omidrezabagherian.karaapplication.ui.doing

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
class DoingViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {

    private val _doingList = MutableStateFlow<List<Task>>(emptyList())
    val doingList: StateFlow<List<Task>> = _doingList.asStateFlow()

    fun setList(taskStatus: TaskStatus) {
        viewModelScope.launch {
            repository.getTaskByStatus(taskStatus).collect { list ->
                _doingList.emit(list)
            }
        }
    }
}