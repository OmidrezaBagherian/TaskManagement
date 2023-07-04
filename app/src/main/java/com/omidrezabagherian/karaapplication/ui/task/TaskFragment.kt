package com.omidrezabagherian.karaapplication.ui.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.omidrezabagherian.karaapplication.R
import com.omidrezabagherian.karaapplication.databinding.FragmentTaskBinding
import com.omidrezabagherian.karaapplication.domian.models.Task
import com.omidrezabagherian.karaapplication.domian.models.TaskStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskFragment : Fragment(R.layout.fragment_task) {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var adapter: TaskAdapter
    private val viewModel: TaskViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTaskBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun navigateToDetail(task: Task) {
        navController.navigate(TaskFragmentDirections.actionGlobalDetailFragment(task))
    }

    private fun setupRecyclerView() {
        viewModel.setList(TaskStatus.TASK)

        adapter = TaskAdapter { task ->
            navigateToDetail(task)
        }

        binding.rvTask.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.taskList.collect { data ->
                    adapter.submitList(data)
                }
            }
        }
    }
}