package com.example.progressivelearning_android.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.User
import com.example.progressivelearning_android.viewmodel.SessionViewModel
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private val sessionViewModel: SessionViewModel by activityViewModels()
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = sessionViewModel.loggedInUser.value!!
        initViews()
    }

    private fun initViews() {
        Log.d("Profile", user.toString())
        tv_user_email.text = user.email
    }


}