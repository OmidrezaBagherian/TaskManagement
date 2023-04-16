package com.omidrezabagherian.taskmanagement.ui.done

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.FragmentDoneBinding
import com.omidrezabagherian.taskmanagement.domian.models.StatusTask
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DoneFragment : Fragment(R.layout.fragment_done) {

    private lateinit var binding: FragmentDoneBinding
    private lateinit var adapter: DoneAdapter
    private val viewModel: DoneViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDoneBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.setList(StatusTask.DONE)

        adapter = DoneAdapter {
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_SHORT).show()
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