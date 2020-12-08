package com.example.progressivelearning_android.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.progressivelearning_android.R
import kotlinx.android.synthetic.main.fragment_detail_units.*

class DetailUnitsFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity, var unitsLength: Int)
        : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = unitsLength

        override fun createFragment(position: Int): Fragment = ScreenSlideUnit()
    }
}


/*
   override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_detail_units)
        viewPager = unitsViewPager
        viewPager.adapter = ScreenSlidePagerAdapter(this, 5)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }*/
