package com.example.progressivelearning_android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Unit
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import kotlinx.android.synthetic.main.fragment_detail_units.*

class DetailUnitsFragment : Fragment() {

    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private lateinit var navController: NavController
    lateinit var learningGoal: LearningGoal
    var currentUnitPos: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_units, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        learningGoal = learningGoalViewModel.selectedLearningGoal.value!!
        val pagerAdapter = ScreenSlidePagerAdapter(this, learningGoal.units)
        unitsViewPager.adapter = pagerAdapter
        initViews()
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment, var units: List<Unit>)
        : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = units.size

        override fun createFragment(position: Int): Fragment {
            return ScreenSlideUnit(units[position])
        }
    }

    private fun initViews() {
        if(learningGoal.units.isEmpty()) {
            iv_forward.visibility = View.GONE
            iv_back.visibility = View.GONE
        }

        iv_forward.setOnClickListener {
            if(currentUnitPos <= learningGoal.units.size-1) {
                unitsViewPager.setCurrentItem(++currentUnitPos, true)
            }
            println(currentUnitPos)
        }

        iv_back.setOnClickListener {
            println(currentUnitPos)
            if(currentUnitPos > 0) {
                unitsViewPager.setCurrentItem(--currentUnitPos, true)
            }
        }

        create_unit_fab.setOnClickListener {
            navController.navigate(R.id.action_collectionLearningGoalFragment_to_createUnitFragment)
        }
    }
}

