package com.example.progressivelearning_android.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.progressivelearning_android.R
import com.example.progressivelearning_android.model.User
import com.example.progressivelearning_android.repository.UserRepository
import com.example.progressivelearning_android.viewmodel.SessionViewModel
import kotlinx.android.synthetic.main.fragment_introduction.*
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.internal.notify

class LoginFragment : Fragment() {

    private val sessionViewModel: SessionViewModel by activityViewModels()
    private lateinit var navController: NavController

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
        sessionViewModel.login(et_email.text.toString(), et_password.text.toString())
        observeSession()
    }

    private fun observeSession() {
        sessionViewModel.authenticationToken.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                val token: String = it
                val sharedPref = requireContext().getSharedPreferences(
                        getString(R.string.session_keys_filename),
                        Context.MODE_PRIVATE)

                with(sharedPref.edit()) {
                    putString(getString(R.string.authentication_token_key), token)
                    apply()
                }
                navController.navigate(R.id.action_navigation_login_to_navigation_explore)
            }
        })
    }

}