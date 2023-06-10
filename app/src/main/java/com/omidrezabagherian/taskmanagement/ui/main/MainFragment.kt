package com.omidrezabagherian.taskmanagement.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.FragmentMainBinding
import com.omidrezabagherian.taskmanagement.ui.ViewPagerAdapter
import com.omidrezabagherian.taskmanagement.util.ConstValues
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val navController by lazy {
        findNavController()
    }

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

        val tabs = resources.getStringArray(R.array.text_tabs)

        TabLayoutMediator(binding.tlStatus, binding.vpStatus) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }

    private fun setupFabInsert() {
        binding.fabInsert.setOnClickListener {
            navController.navigate(MainFragmentDirections.actionMainFragmentToInsertFragment())
        }
    }
}