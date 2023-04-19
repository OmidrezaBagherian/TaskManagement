package com.omidrezabagherian.taskmanagement.ui.doing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.FragmentDoingBinding
import com.omidrezabagherian.taskmanagement.domian.models.StatusTask
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoingFragment : Fragment(R.layout.fragment_doing) {
    private lateinit var binding: FragmentDoingBinding
    private lateinit var adapter: DoingAdapter
    private val viewModel: DoingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDoingBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.setList(StatusTask.DOING)

        adapter = DoingAdapter {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
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