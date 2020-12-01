package com.example.progressivelearning_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.adapter.LearningGoalAdapter
import com.example.progressivelearning_android.model.LearningGoal
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashboardFragment : Fragment() {

    var learningGoals: ArrayList<LearningGoal> = arrayListOf(
            LearningGoal("Understanding the REST principles",
            "The REST principle is a software architectural design for web services",
            20, arrayListOf(), null)
    )
    private val learningGoalAdapter: LearningGoalAdapter = LearningGoalAdapter(learningGoals)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        learning_goals_rv.adapter = learningGoalAdapter
        learning_goals_rv.layoutManager = LinearLayoutManager(context,
                RecyclerView.VERTICAL, false)
    }



}