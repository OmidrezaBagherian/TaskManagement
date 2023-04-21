package com.omidrezabagherian.taskmanagement.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omidrezabagherian.taskmanagement.data.TaskManagementRepository
import com.omidrezabagherian.taskmanagement.domian.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
}