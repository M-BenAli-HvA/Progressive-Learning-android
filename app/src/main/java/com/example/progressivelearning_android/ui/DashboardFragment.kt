package com.example.progressivelearning_android.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.adapter.LearningGoalAdapter
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Subject
import com.example.progressivelearning_android.model.User
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import com.example.progressivelearning_android.viewmodel.SessionViewModel
import com.example.progressivelearning_android.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_resources_dialog.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashboardFragment : Fragment() {

    private val TAG = "DashboardFragment"

    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private val subjectsViewModel: SubjectViewModel by activityViewModels()
    private val sessionViewModel: SessionViewModel by activityViewModels()

    private var learningGoals: ArrayList<LearningGoal> = arrayListOf()
    private var subjects: ArrayList<Subject> = arrayListOf()

    private val learningGoalAdapter: LearningGoalAdapter = LearningGoalAdapter(learningGoals, ::onClick)
    private lateinit var adapter: ArrayAdapter<Subject>
    private lateinit var sharedPref: SharedPreferences
    private lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        sharedPref = requireContext().getSharedPreferences(
                getString(R.string.session_keys_filename), Context.MODE_PRIVATE)
        initViews()
        observeLearningGoals()
        observeSubjects()
        adapter = ArrayAdapter(requireContext(), R.layout.dropdown_list_item, subjects)
        (tip_subject_dropdown.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        (tip_subject_dropdown.editText as? AutoCompleteTextView)?.setOnItemClickListener { _, _, i, _ ->
            if(subjects[i].id == 0) {
                observeLearningGoals()
            } else {
                val subject = subjects[i]
                val filteredLearningGoals: ArrayList<LearningGoal> = arrayListOf()
                for (learningGoal in learningGoals) {
                   if (subject.id != learningGoal.subjectId) filteredLearningGoals.add(learningGoal)
                }
                learningGoals.removeAll(filteredLearningGoals)
            }
            Log.d(TAG, learningGoals.toString())
            learningGoalAdapter.notifyDataSetChanged()
        }
    }


    private fun initViews() {
        val token: String = sharedPref.getString(getString(R.string.authentication_token_key), null)!!
        val user: User = sessionViewModel.loggedInUser.value!!
        learningGoalViewModel.getUserLearningGoals(user, token)
        subjectsViewModel.getUserSubjects(user.id!!, token)
        learning_goals_rv.adapter = learningGoalAdapter
        learning_goals_rv.layoutManager = LinearLayoutManager(context,
                RecyclerView.VERTICAL, false)

        fab.setOnClickListener {
            navController.navigate(R.id.action_navigation_dashboard_to_createLearningGoalFragment)
        }
    }

    private fun observeLearningGoals() {
        learningGoalViewModel.learningGoals.observe(viewLifecycleOwner, Observer {
            learningGoals.clear()
            learningGoals.addAll(it)
            learningGoalAdapter.notifyDataSetChanged()
        })
    }

    private fun observeSubjects() {
        subjectsViewModel.subjects.observe(viewLifecycleOwner, Observer {
            subjects.clear()
            subjects.add(Subject(id = 0, title = "All"))
            subjects.addAll(1, it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun onClick(learningGoal: LearningGoal) {
        learningGoalViewModel.setLearningGoal(learningGoal)
        navController.navigate(R.id.action_navigation_dashboard_to_collectionLearningGoalFragment)
    }

}