package com.example.progressivelearning_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.User
import com.example.progressivelearning_android.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_introduction.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    val userRepository: UserRepository = UserRepository()
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        initViews()
    }

    private fun initViews() {
        login_btn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if(userRepository.login(et_email.text.toString(), et_password.text.toString())) {
            navController.navigate(R.id.action_navigation_login_to_navigation_explore)
        }
    }

}