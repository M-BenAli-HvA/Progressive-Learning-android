package com.example.progressivelearning_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.adapter.LearningGoalAdapter
import com.example.progressivelearning_android.adapter.ViewpagerAdapter
import com.example.progressivelearning_android.viewmodel.LearningGoalViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_collection_detail.*


class CollectionLearningGoalFragment : Fragment() {

    private val learningGoalViewModel: LearningGoalViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = ViewpagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) {
            tab, position ->
            when(position) {
                0 -> tab.text = "General"
                1 -> tab.text = "Units"
            }

        }.attach()
    }

    



}