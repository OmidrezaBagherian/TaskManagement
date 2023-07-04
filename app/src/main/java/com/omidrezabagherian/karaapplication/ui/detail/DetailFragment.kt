package com.omidrezabagherian.karaapplication.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.omidrezabagherian.karaapplication.R
import com.omidrezabagherian.karaapplication.databinding.FragmentDetailBinding
import com.omidrezabagherian.karaapplication.domian.models.Task
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
    private lateinit var task: Task
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        setupUI()

    }

    private fun setupUI() {
        bindDataToUI(navArgs.task.id)
        setupMenu()
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_update -> {
                        manageUpdateTask(task)
                        true
                    }

                    R.id.menu_remove -> {
                        manageDeleteTask(task)
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun bindDataToUI(id: Int) {
        detailViewModel.taskById(id)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                detailViewModel.detail.collect { data ->
                    binding.mtvTitle.text = data.title
                    binding.mtvStatus.text = data.taskStatus.name
                    binding.mtvDescription.text = data.description
                    task = data
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

    private fun manageUpdateTask(task: Task){
        navigationToEdit(task)
    }
    private fun manageDeleteTask(task: Task) {
        detailViewModel.deleteTask(task)
        navigateToMain()
    }
}