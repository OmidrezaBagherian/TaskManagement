package com.omidrezabagherian.karaapplication.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.omidrezabagherian.karaapplication.R
import com.omidrezabagherian.karaapplication.databinding.FragmentMainBinding
import com.omidrezabagherian.karaapplication.ui.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val navController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupTabLayout()
        setupFabInsert()
        setupMenu()
    }

    private fun navigateToSetting(){
        navController.navigate(MainFragmentDirections.actionMainFragmentToSettingFragment())
    }

    private fun navigateToAboutMe(){
        navController.navigate(MainFragmentDirections.actionMainFragmentToAboutMeFragment())
    }

    private fun setupMenu() {
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_setting -> {
                        navigateToSetting()
                        true
                    }

                    R.id.menu_about -> {
                        navigateToAboutMe()
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupTabLayout() {
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
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