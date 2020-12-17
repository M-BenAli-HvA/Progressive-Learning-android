package com.example.progressivelearning_android.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.LearningGoal
import com.example.progressivelearning_android.model.Unit
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_create_unit.*

class CreateUnitFragment : Fragment() {

    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()
    private lateinit var navController: NavController
    private lateinit var learningGoal: LearningGoal
    private var unit = Unit("", false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_unit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        learningGoal = learningGoalViewModel.selectedLearningGoal.value!!
        navController = findNavController()
        initViews()
    }

    private fun initViews() {
        btn_resources.setOnClickListener {
            displayDialog()
        }

        btn_create_unit.setOnClickListener {
            createUnit()
            navController.popBackStack()
            Toast.makeText(requireContext(), R.string.created_unit_message, Toast.LENGTH_LONG).show()
        }
    }

    private fun createUnit() {
        unit.title = et_title.text.toString()
        unit.summary = et_summary.text.toString()
        learningGoalViewModel.setNewUnit(unit)
        learningGoalViewModel.addNewUnit()
        Log.e("CreateUnit", unit.toString())
    }

    private fun displayDialog() {
        val fragmentManager = requireActivity().supportFragmentManager
        val newFragment = ResourcesDialogFragment(unit)
        newFragment.show(fragmentManager, "dialog")
    }

}