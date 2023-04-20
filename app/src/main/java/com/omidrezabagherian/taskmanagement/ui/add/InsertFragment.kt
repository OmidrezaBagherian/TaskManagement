package com.omidrezabagherian.taskmanagement.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.FragmentInsertBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertFragment : Fragment(R.layout.fragment_insert) {

    private lateinit var binding: FragmentInsertBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInsertBinding.bind(view)

    }
}