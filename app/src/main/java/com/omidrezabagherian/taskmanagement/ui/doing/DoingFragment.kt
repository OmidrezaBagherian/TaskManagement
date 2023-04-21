package com.omidrezabagherian.taskmanagement.ui.doing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.FragmentDoingBinding
import com.omidrezabagherian.taskmanagement.domian.models.Task
import com.omidrezabagherian.taskmanagement.domian.models.TaskStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoingFragment : Fragment(R.layout.fragment_doing) {
    private lateinit var binding: FragmentDoingBinding
    private lateinit var adapter: DoingAdapter
    private val viewModel: DoingViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDoingBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun navigateToDetail(task: Task) {
        navController.navigate(DoingFragmentDirections.actionGlobalDetailFragment(task))
    }

    private fun setupRecyclerView() {
        viewModel.setList(TaskStatus.DOING)

        adapter = DoingAdapter { task ->
            navigateToDetail(task)
        }

        binding.rvDoing.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.doingList.collect { data ->
                    adapter.submitList(data)
                }
            }
        }
    }
}