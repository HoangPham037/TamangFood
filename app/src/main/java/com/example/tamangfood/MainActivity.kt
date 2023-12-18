package com.example.tamangfood

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import androidx.navigation.ui.setupWithNavController
import com.example.tamangfood.databinding.ActivityMainBinding
import com.example.tamangfood.extensions.gone
import com.example.tamangfood.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @OptIn(NavigationUiSaveStateControl::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        if (::navController.isInitialized) {
            NavigationUI.setupWithNavController(binding.bottomNav, navController,false)
        }
        binding.bottomNav.setupWithNavController(navController)
        navHostFragment.navController.addOnDestinationChangedListener { controller, _, _ ->
           val backstack = controller.backQueue
                .joinToString(separator = "\n", prefix = "backStack: [ ", postfix = " ]") { entry ->
                    buildString {
                        append(" idName=")
                        append(entry.destination.displayName)
                        append(" UID=")
                        append(" args=")
                        append(entry.arguments?.let { args -> args.keySet().associateWith { args.get(it) } })
                    }
                }
            Log.v("2222", backstack)
        }
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
                R.id.singleRestaurantFragment -> hideBottomNav()
                R.id.resultSearchingFragment -> hideBottomNav()
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