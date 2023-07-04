package com.omidrezabagherian.karaapplication.ui.insert

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.omidrezabagherian.karaapplication.R
import com.omidrezabagherian.karaapplication.databinding.FragmentInsertBinding
import com.omidrezabagherian.karaapplication.domian.models.TaskStatus
import com.omidrezabagherian.karaapplication.domian.models.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertFragment : Fragment(R.layout.fragment_insert) {

    private lateinit var binding: FragmentInsertBinding
    private val insertViewModel: InsertViewModel by viewModels()
    private val nacController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInsertBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        setupBtnInsert()
        setAdapterAutoComplete()
    }

    private fun setAdapterAutoComplete() {
        val status = resources.getStringArray(R.array.text_insert_status)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, status)
        binding.actvStatus.setAdapter(adapter)
    }

    private fun getStatus(): TaskStatus {
        val status:Array<String> = resources.getStringArray(R.array.text_insert_status)
        return when (binding.actvStatus.text.toString()) {
            status[0] -> {
                TaskStatus.TASK
            }

            status[1] -> {
                TaskStatus.DOING
            }

            status[2] -> {
                TaskStatus.DONE
            }

            else -> {
                TaskStatus.NONE
            }
        }
    }

    private fun insertTaskToDatabase(task: Task) {
        insertViewModel.insertTask(task)
    }

    private fun checkFields() {
        if (binding.tiedTitle.text!!.isEmpty()) {
            Snackbar.make(
                requireView(),
                getString(R.string.text_toast_title_empty),
                Snackbar.LENGTH_SHORT
            ).setAction(getString(R.string.text_toast_okey)) {
                isHidden
            }.show()
            return
        }

        if (getStatus() == TaskStatus.NONE) {
            Snackbar.make(
                requireView(),
                getString(R.string.text_toast_task_not_selected),
                Snackbar.LENGTH_SHORT
            ).setAction(getString(R.string.text_toast_okey)) {
                isHidden
            }.show()
            return
        }

        insertTaskToDatabase(
            Task(
                0,
                binding.tiedTitle.text.toString(),
                binding.tiedDescription.text.toString(),
                getStatus()
            )
        )
        navigateToMain()
    }

    private fun navigateToMain() {
        nacController.navigate(InsertFragmentDirections.actionInsertFragmentToMainFragment())
    }

    private fun setupBtnInsert() {
        binding.mbtnInsert.setOnClickListener {
            checkFields()
        }
    }
}