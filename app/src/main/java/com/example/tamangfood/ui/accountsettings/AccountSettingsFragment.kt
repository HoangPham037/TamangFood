package com.example.tamangfood.ui.accountsettings

import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentAccountSettingsBinding

class AccountSettingsFragment : BaseFragment<FragmentAccountSettingsBinding>(
    FragmentAccountSettingsBinding::inflate
) {
    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.layoutSettings.tvGotoSettingProfile.setOnClickListener {
            findNavController().navigate(R.id.action_accountSettingsFragment_to_profileSettingsFragment)
        }
    }
}