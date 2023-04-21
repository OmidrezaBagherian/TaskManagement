package com.omidrezabagherian.taskmanagement.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omidrezabagherian.taskmanagement.data.TaskManagementRepository
import com.omidrezabagherian.taskmanagement.domian.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {

    private val _detail = MutableSharedFlow<Task>()
    val detail: SharedFlow<Task> = _detail.asSharedFlow()

    fun taskById(id: Int) {
        viewModelScope.launch {
            repository.getTaskById(id).collect { task ->
                _detail.emit(task)
            }
        }
    }

}