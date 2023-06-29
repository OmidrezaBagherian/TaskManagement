package com.omidrezabagherian.taskmanagement.ui.about

import androidx.lifecycle.ViewModel
import com.omidrezabagherian.taskmanagement.data.TaskManagementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutMeViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {
}