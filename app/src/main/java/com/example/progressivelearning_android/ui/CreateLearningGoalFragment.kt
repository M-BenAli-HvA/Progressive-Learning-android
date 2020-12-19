package com.example.progressivelearning_android.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Subject
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import com.example.progressivelearning_android.viewmodel.SessionViewModel
import com.example.progressivelearning_android.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_create_learning_goal.*
import kotlinx.android.synthetic.main.fragment_create_learning_goal.tip_subject_dropdown
import kotlinx.android.synthetic.main.fragment_dashboard.*

class CreateLearningGoalFragment : Fragment() {

    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private val subjectViewModel: SubjectViewModel by activityViewModels()
    private val sessionViewModel: SessionViewModel by activityViewModels()

    private val subjects: ArrayList<Subject> = arrayListOf()
    private var selectedSubject: Subject? = null
    private lateinit var navController: NavController
    private lateinit var adapter: ArrayAdapter<Subject>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_learning_goal, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setHasOptionsMenu(true)
        initViews()
        observeSubjects()
        adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_item, subjects)
        (tip_subject_dropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (tip_subject_dropdown.editText as? AutoCompleteTextView)?.setOnItemClickListener { _, _, i, _ ->
            selectedSubject = subjects[i]
        }
    }


    private fun initViews() {
        create_btn.setOnClickListener {
            createLearningGoal()
        }
    }

    private fun observeSubjects() {
        subjectViewModel.subjects.observe(viewLifecycleOwner, Observer {
            subjects.clear()
            subjects.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun createLearningGoal() {
        val user = sessionViewModel.loggedInUser.value
        val learningGoal = LearningGoal(et_title.text.toString(),
        et_description.text.toString(), user = user, subject = selectedSubject)
        Log.d("learningGoalFrag", learningGoal.toString())
        learningGoalViewModel.createLearningGoal(learningGoal)
        navController.navigate(R.id.action_createLearningGoalFragment_to_navigation_dashboard)
    }

}