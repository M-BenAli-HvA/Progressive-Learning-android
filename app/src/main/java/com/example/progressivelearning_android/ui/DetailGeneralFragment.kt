package com.example.progressivelearning_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import kotlinx.android.synthetic.main.fragment_detail_general.*

class DetailGeneralFragment : Fragment() {

    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private lateinit var learningGoal: LearningGoal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        learningGoal = learningGoalViewModel.selectedLearningGoal.value!!
        initView()
    }

    private fun initView() {
        tv_lg_title.text = learningGoal.title
        tv_lg_description.text = learningGoal.description
        progress_bar.progress = learningGoal.progress
        tv_progress_percentage.text = "${learningGoal.progress}%"
    }

}