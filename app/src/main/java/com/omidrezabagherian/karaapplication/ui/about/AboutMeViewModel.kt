package com.omidrezabagherian.karaapplication.ui.about

import androidx.lifecycle.ViewModel
import com.omidrezabagherian.karaapplication.data.TaskManagementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutMeViewModel @Inject constructor(private val repository: TaskManagementRepository) :
    ViewModel() {
}