package com.omidrezabagherian.taskmanagement.ui.main

import androidx.lifecycle.ViewModel
import com.omidrezabagherian.taskmanagement.data.TaskManagementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {


}