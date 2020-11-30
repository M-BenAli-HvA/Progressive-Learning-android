package com.example.progressivelearning_android.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    private val userRepository: UserRepository = UserRepository()
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        initViews()
    }

    private fun initViews() {
        signup_btn.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
       if(userRepository.signUp(et_email.text.toString(),
                et_password.text.toString(), et_confirm_password.text.toString())) {
           return navController.navigate(R.id.action_navigation_signup_to_navigation_explore)
       }
    }

}