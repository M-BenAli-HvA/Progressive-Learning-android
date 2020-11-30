package com.example.progressivelearning_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.progressivelearning_android.R
import kotlinx.android.synthetic.main.fragment_introduction.*


class IntroductionFragment : Fragment() {

    val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        signup_button.setOnClickListener {
            navController.navigate(R.id.action_navigation_introduction_to_signup_fragment)
        }

        login_button.setOnClickListener {
            navController.navigate(R.id.action_navigation_introduction_to_navigation_login)
        }
    }
}