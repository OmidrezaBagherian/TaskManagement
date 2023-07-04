package com.omidrezabagherian.karaapplication.ui.insert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omidrezabagherian.karaapplication.data.TaskManagementRepository
import com.omidrezabagherian.karaapplication.domian.models.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {

    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }
}