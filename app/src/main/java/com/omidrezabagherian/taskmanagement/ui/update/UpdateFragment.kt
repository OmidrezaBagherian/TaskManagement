package com.omidrezabagherian.taskmanagement.ui.update

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.omidrezabagherian.taskmanagement.R
import com.omidrezabagherian.taskmanagement.databinding.FragmentUpdateBinding
import com.omidrezabagherian.taskmanagement.domian.models.Task
import com.omidrezabagherian.taskmanagement.domian.models.TaskStatus
import com.omidrezabagherian.taskmanagement.ui.insert.InsertFragmentDirections
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
    }

    private fun isCheckedStatus(){
        when(navArgs.task.taskStatus){
            TaskStatus.TASK->{
                binding.rbTask.isChecked = true
            }
            TaskStatus.DOING->{
                binding.rbDoing.isChecked = true
            }
            TaskStatus.DONE->{
                binding.rbDone.isChecked = true
            }
            else -> {
                binding.rbTask.isChecked = true
            }
        }
    }

    private fun bindDataToUI() {
        binding.tiedTitle.setText(navArgs.task.title)
        isCheckedStatus()
        binding.tiedDescription.setText(navArgs.task.title)
    }

    private fun getStatus(): TaskStatus =
        when (binding.rgStatus.checkedRadioButtonId) {
            R.id.rbTask -> {
                TaskStatus.TASK
            }
            R.id.rbDoing -> {
                TaskStatus.DOING
            }
            R.id.rbDone -> {
                TaskStatus.DONE
            }
            else -> {
                TaskStatus.NONE
            }
        }

    private fun insertTaskToDatabase(task: Task) {
        insertViewModel.updateTask(task)
    }

    private fun checkFields() {
        if (binding.tiedTitle.text!!.isEmpty()) {
            Snackbar.make(
                requireView(),
                "Title is empty.",
                Snackbar.LENGTH_SHORT
            ).setAction("OK") {
                isHidden
            }.show()
            return
        }

        if (getStatus() == TaskStatus.NONE) {
            Snackbar.make(
                requireView(),
                "Task status not selected.",
                Snackbar.LENGTH_SHORT
            ).setAction("OK") {
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