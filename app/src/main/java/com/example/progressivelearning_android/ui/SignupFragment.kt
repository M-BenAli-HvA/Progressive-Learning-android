package com.example.progressivelearning_android.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    private val TAG = "SignUpFragment"
    private val sessionViewModel: SessionViewModel by activityViewModels()
    private var signedUp: Boolean = false
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
        sessionViewModel.signUp(et_email.text.toString(),
                et_password.text.toString(), et_confirm_password.text.toString())
        observeSession()
    }

    private fun observeSession() {
        sessionViewModel.authenticationToken.observe(viewLifecycleOwner, Observer {
            val token: String? = it
            Log.d(TAG, token.toString())
            if (token != null && !signedUp) {
                val sharedPref = requireContext().getSharedPreferences(
                        getString(R.string.session_keys_filename),
                        Context.MODE_PRIVATE)

                with(sharedPref.edit()) {
                    putString(getString(R.string.authentication_token_key), token)
                    apply()
                }
                signedUp = true
                Log.d(TAG, "About to navigate to explore fragment..")
                navController.navigate(R.id.action_navigation_signup_to_navigation_explore)
            }
        })
    }

}
