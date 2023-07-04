package com.omidrezabagherian.taskmanagement.ui.setting

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.data.datastore.Theme
import com.omidrezabagherian.taskmanagement.databinding.FragmentSettingBinding
import com.omidrezabagherian.taskmanagement.ui.MainViewModel

class SettingFragment : Fragment(R.layout.fragment_setting) {

    private lateinit var binding: FragmentSettingBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingBinding.bind(view)

        setupUI()

    }

    private fun setupUI() {
        Thread {
            requireActivity().runOnUiThread {
                choiceTheme()
                checkTheme()
            }
        }.start()
    }

    private fun defaultTheme() {
        binding.sbTheme.progress = 0
        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            == Configuration.UI_MODE_NIGHT_YES
        ) {
            binding.lottieTheme.setAnimation(R.raw.night)
        } else {
            binding.lottieTheme.setAnimation(R.raw.day)
        }
    }

    private fun dayTheme() {
        binding.sbTheme.progress = 1
        binding.lottieTheme.setAnimation(R.raw.day)
    }

    private fun nightTheme() {
        binding.sbTheme.progress = 2
        binding.lottieTheme.setAnimation(R.raw.night)
    }

    private fun checkTheme() {
        viewModel.getTheme().observe(viewLifecycleOwner) { theme ->
            when (theme) {
                Theme.DEFAULT.name -> defaultTheme()
                Theme.LIGHT.name -> dayTheme()
                Theme.NIGHT.name -> nightTheme()
            }
        }
    }

    private fun choiceTheme() {
        binding.sbTheme.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    0 -> viewModel.setTheme(Theme.DEFAULT.name)
                    1 -> viewModel.setTheme(Theme.LIGHT.name)
                    2 -> viewModel.setTheme(Theme.NIGHT.name)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

}