package com.example.progressivelearning_android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter.POSITION_NONE
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Unit
import com.example.progressivelearning_android.repository.UnitRepository
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import com.example.progressivelearning_android.viewmodel.SessionViewModel
import kotlinx.android.synthetic.main.fragment_detail_units.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUnitsFragment : Fragment() {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val unitRepository: UnitRepository = UnitRepository()
    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private val sessionViewModel: SessionViewModel by activityViewModels()
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter
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
        observeSelectedLearningGoal()
        pagerAdapter = ScreenSlidePagerAdapter(this, learningGoal.units)
        unitsViewPager.adapter = pagerAdapter
        initViews()
    }

    override fun onResume() {
        super.onResume()
        observeSelectedLearningGoal()
//        learningGoal = learningGoalViewModel.selectedLearningGoal.value!!
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment, var units: List<Unit>)
        : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = units.size

        override fun createFragment(position: Int): Fragment {
            val unit = units[position]
            if (unit.id != 0) {
                unit.resources = arrayListOf()
                mainScope.launch {
                    unit.resources.addAll(unitRepository.getUnitResources(unit.id!!))
                }
            }
            return ScreenSlideUnit(unit)
        }

    }

    private fun initViews() {
        if (learningGoal.units.isEmpty()) {
            iv_forward.visibility = View.GONE
            iv_back.visibility = View.GONE
        }

        iv_forward.setOnClickListener {
            if (currentUnitPos <= learningGoal.units.size - 1) {
                unitsViewPager.setCurrentItem(++currentUnitPos, true)
            }
            println(currentUnitPos)
        }

        iv_back.setOnClickListener {
            println(currentUnitPos)
            if (currentUnitPos > 0) {
                unitsViewPager.setCurrentItem(--currentUnitPos, true)
            }
        }


        create_unit_fab.setOnClickListener {
            navController.navigate(R.id.action_collectionLearningGoalFragment_to_createUnitFragment)
        }

        if(sessionViewModel.loggedInUser.value?.id!! != learningGoal.userId) {
            create_unit_fab.visibility = View.GONE
        }
    }

    private fun observeSelectedLearningGoal() {
        learningGoalViewModel.selectedLearningGoal.observe(viewLifecycleOwner, Observer {
            learningGoal = it
            pagerAdapter.notifyDataSetChanged()
        })
    }
}

