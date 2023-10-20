package com.example.tamangfood.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentWelcomeBinding
import com.example.tamangfood.extensions.setSafeOnClickListener


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(
    FragmentWelcomeBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBtnGetStart.setSafeOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_onBoardingContainerFragment)
        }
    }
}