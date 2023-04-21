package com.omidrezabagherian.taskmanagement.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.FragmentDetailBinding
import com.omidrezabagherian.taskmanagement.domian.models.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }
    private val navArgs: DetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        setupUI()

    }

    private fun setupUI() {
        bindDataToUI(navArgs.task.id)
    }

    private fun bindDataToUI(id: Int) {
        detailViewModel.taskById(id)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                detailViewModel.detail.collect { data ->
                    binding.mtvTitle.text = data.title
                    binding.mtvStatus.text = data.taskStatus.name
                    binding.mtvDescription.text = data.description
                    btnDeleteTask(data)
                    btnUpdateTask(data)
                }
            }
        }
    }

    private fun navigateToMain(){
        navController.navigate(DetailFragmentDirections.actionDetailFragmentToMainFragment())
    }

    private fun navigationToEdit(task: Task){
        navController.navigate(DetailFragmentDirections.actionDetailFragmentToUpdateFragment(task))
    }

    private fun btnUpdateTask(task: Task){
        binding.mbtnEdit.setOnClickListener {
            navigationToEdit(task)
        }
    }
    private fun btnDeleteTask(task: Task) {
        binding.mbtnRemove.setOnClickListener {
            detailViewModel.deleteTask(task)
            navigateToMain()
        }
    }
}