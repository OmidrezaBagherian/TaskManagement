package com.omidrezabagherian.taskmanagement.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.ActivityMainBinding
import com.omidrezabagherian.taskmanagement.databinding.FragmentMainBinding
import com.omidrezabagherian.taskmanagement.ui.ViewPagerAdapter
import com.omidrezabagherian.taskmanagement.util.ConstValues

class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding:FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentMainBinding.bind(view)

        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = ConstValues.TAB_EN[position]
        }.attach()
    }
}