package com.example.diceroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.diceroller.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.mainNavView

        val navController = findNavController(com.example.diceroller.R.id.main_nav_host_fragment_activity_main2)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                com.example.diceroller.R.id.navigation_login, com.example.diceroller.R.id.navigation_register, com.example.diceroller.R.id.navigation_reset_password
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}