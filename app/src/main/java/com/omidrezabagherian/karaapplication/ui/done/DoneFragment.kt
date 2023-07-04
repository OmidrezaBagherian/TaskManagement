package com.omidrezabagherian.karaapplication.ui.done

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.omidrezabagherian.karaapplication.R
import com.omidrezabagherian.karaapplication.databinding.FragmentDoneBinding
import com.omidrezabagherian.karaapplication.domian.models.Task
import com.omidrezabagherian.karaapplication.domian.models.TaskStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoneFragment : Fragment(R.layout.fragment_done) {

    private lateinit var binding: FragmentDoneBinding
    private lateinit var adapter: DoneAdapter
    private val viewModel: DoneViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDoneBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun navigateToDetail(task: Task) {
        navController.navigate(DoneFragmentDirections.actionGlobalDetailFragment(task))
    }

    private fun setupRecyclerView() {
        viewModel.setList(TaskStatus.DONE)

        adapter = DoneAdapter { task ->
            navigateToDetail(task)
        }

        binding.rvDone.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.doneList.collect { data ->
                    adapter.submitList(data)
                }
            }
        }
    }
}