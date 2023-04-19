package com.omidrezabagherian.taskmanagement.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.ActivityMainBinding
import com.omidrezabagherian.taskmanagement.databinding.FragmentMainBinding
import com.omidrezabagherian.taskmanagement.domian.models.StatusTask
import com.omidrezabagherian.taskmanagement.domian.models.Task
import com.omidrezabagherian.taskmanagement.ui.ViewPagerAdapter
import com.omidrezabagherian.taskmanagement.util.ConstValues
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentMainBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupTabLayout()
        setupFabInsert()
    }

    private fun setupTabLayout() {
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.vpStatus.adapter = adapter

        TabLayoutMediator(binding.tlStatus, binding.vpStatus) { tab, position ->
            tab.text = ConstValues.TAB_EN[position]
        }.attach()
    }

    private fun setupFabInsert() {
        binding.fabInsert.setOnClickListener {
            Snackbar.make(requireView(), "Insert Task", Snackbar.LENGTH_SHORT).show()
            viewLifecycleOwner.lifecycleScope.launch {
                mainViewModel.insertTask(
                    Task(0, "Test", "Test", StatusTask.TASK)
                )
            }

        }
    }
}