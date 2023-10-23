package com.example.tamangfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tamangfood.databinding.ActivityMainBinding
import com.example.tamangfood.extensions.gone
import com.example.tamangfood.extensions.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
//        NavigationUI.setupWithNavController(binding.bottomNav, navController, false)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onBoardingContainerFragment -> hideBottomNav()
                R.id.welcomeFragment -> hideBottomNav()
                R.id.signInFragment -> hideBottomNav()
                R.id.signUpFragment -> hideBottomNav()
                R.id.addToOrdersFragment -> hideBottomNav()
                R.id.yourOrdersFragment -> hideBottomNav()
                R.id.paymentMethodFragment -> hideBottomNav()
                R.id.myPaymentMethodsFragment -> hideBottomNav()
                R.id.addPaymentFragment -> hideBottomNav()
                R.id.searchChildFragment-> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNav.visible()
    }

    private fun hideBottomNav() {
        binding.bottomNav.gone()
    }

}