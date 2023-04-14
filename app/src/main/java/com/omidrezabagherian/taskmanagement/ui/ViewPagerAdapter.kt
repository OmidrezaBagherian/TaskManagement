package com.omidrezabagherian.taskmanagement.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.omidrezabagherian.taskmanagement.ui.doing.DoingFragment
import com.omidrezabagherian.taskmanagement.ui.done.DoneFragment
import com.omidrezabagherian.taskmanagement.ui.task.TaskFragment
import com.omidrezabagherian.taskmanagement.util.ConstValues

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = ConstValues.NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TaskFragment()
            1 -> DoingFragment()
            2 -> DoneFragment()
            else -> TaskFragment()
        }
    }

}