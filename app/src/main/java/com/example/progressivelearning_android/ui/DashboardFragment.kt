package com.example.progressivelearning_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.adapter.LearningGoalAdapter
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashboardFragment : Fragment() {

    private lateinit var navController: NavController
    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private val learningGoals: ArrayList<LearningGoal> = arrayListOf()
    private val learningGoalAdapter: LearningGoalAdapter = LearningGoalAdapter(learningGoals, ::onClick)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        learningGoals.addAll(learningGoalViewModel.learningGoals)
        learning_goals_rv.adapter = learningGoalAdapter
        learning_goals_rv.layoutManager = LinearLayoutManager(context,
                RecyclerView.VERTICAL, false)
    }

    private fun initViews() {

    }

    private fun onClick(learningGoal: LearningGoal) {
        learningGoalViewModel.setLearningGoal(learningGoal)
        println(learningGoalViewModel.selectedLearningGoal.value!!.title)
        navController.navigate(R.id.action_navigation_dashboard_to_collectionLearningGoalFragment)
    }



}