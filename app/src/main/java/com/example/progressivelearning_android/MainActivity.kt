package com.example.progressivelearning_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        navController = findNavController(R.id.nav_host_fragment)
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav)
        val appbarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_explore, R.id.navigation_dashboard,
                R.id.navigation_profile
        ))
        setupActionBarWithNavController(navController, appbarConfiguration)
        bottomNavView.setupWithNavController(navController)
        toggleBottomNav()
    }

    private fun toggleBottomNav() {
        val bottomAppBar: BottomAppBar = findViewById(R.id.bottom_appbar)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            run {
                when(destination.id) {
                    R.id.navigation_introduction, R.id.navigation_signup,
                    R.id.navigation_login-> {
                        supportActionBar?.hide()
                        bottomAppBar.visibility = View.GONE
                    }
                    else  ->{
                        supportActionBar?.show()
                        bottomAppBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}