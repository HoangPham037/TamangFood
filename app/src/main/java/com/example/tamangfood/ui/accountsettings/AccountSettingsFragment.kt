package com.example.tamangfood.ui.accountsettings

import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentAccountSettingsBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountSettingsFragment : BaseFragment<FragmentAccountSettingsBinding>(
    FragmentAccountSettingsBinding::inflate
) {
    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.layoutSettings.tvGotoSettingProfile.setSafeOnClickListener {
            findNavController().navigate(R.id.action_accountSettingsFragment_to_profileSettingsFragment)
        }
        binding.layoutSettings.imgGotoPayment.setSafeOnClickListener {
            findNavController().navigate(R.id.action_accountSettingsFragment_to_paymentMethodFragment)
        }
        binding.layoutMore.layoutLogout.setSafeOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_accountSettingsFragment_to_signInFragment)
        }
    }
}