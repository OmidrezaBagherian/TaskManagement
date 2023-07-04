package com.omidrezabagherian.karaapplication.ui.update

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.omidrezabagherian.karaapplication.domian.models.Task
import com.omidrezabagherian.karaapplication.domian.models.TaskStatus
import com.omidrezabagherian.karaapplication.R
import com.omidrezabagherian.karaapplication.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.fragment_update) {

    private lateinit var binding: FragmentUpdateBinding
    private val insertViewModel: UpdateViewModel by viewModels()
    private val nacController by lazy {
        findNavController()
    }
    private val navArgs: UpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUpdateBinding.bind(view)

        setupUI()
    }

    private fun setupUI() {
        bindDataToUI()
        setupBtnUpdate()
        setAdapterAutoComplete()
    }

    private fun setAdapterAutoComplete() {
        val status = resources.getStringArray(R.array.text_insert_status)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, status)
        binding.actvStatus.setAdapter(adapter)
    }

    private fun isCheckedStatus() {
        val status: Array<String> = resources.getStringArray(R.array.text_insert_status)

        when (navArgs.task.taskStatus) {
            TaskStatus.TASK -> {
                binding.actvStatus.setText(status[0])
            }

            TaskStatus.DOING -> {
                binding.actvStatus.setText(status[1])
            }

            TaskStatus.DONE -> {
                binding.actvStatus.setText(status[2])
            }

            else -> {
                Snackbar.make(
                    requireView(),
                    getString(R.string.text_toast_task_loaded),
                    Snackbar.LENGTH_SHORT
                ).setAction(getString(R.string.text_toast_okey)) {
                    isHidden
                }.show()
            }
        }
    }

    private fun bindDataToUI() {
        binding.tiedTitle.setText(navArgs.task.title)
        isCheckedStatus()
        binding.tiedDescription.setText(navArgs.task.description)
    }

    private fun getStatus(): TaskStatus {
        val status: Array<String> = resources.getStringArray(R.array.text_insert_status)
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
        insertViewModel.updateTask(task)
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
                navArgs.task.id,
                binding.tiedTitle.text.toString(),
                binding.tiedDescription.text.toString(),
                getStatus()
            )
        )
        navigateToMain()
    }

    private fun navigateToMain() {
        nacController.navigate(UpdateFragmentDirections.actionUpdateFragmentToMainFragment())
    }

    private fun setupBtnUpdate() {
        binding.mbtnUpdate.setOnClickListener {
            checkFields()
        }
    }

}