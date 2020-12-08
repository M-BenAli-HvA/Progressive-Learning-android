package com.example.progressivelearning_android.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.progressivelearning_android.ui.DetailGeneralFragment
import com.example.progressivelearning_android.ui.DetailUnitsFragment


class ViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        var fragment: Fragment = DetailGeneralFragment()
        when(position) {
            0 -> {
                fragment = DetailGeneralFragment()
            }
            1 -> {
                fragment = DetailUnitsFragment()
            }
        }

        return fragment
    }
}
