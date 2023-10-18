package com.example.tamangfood.ui.accountsettings.profilesettings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentProfileSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileSettingsFragment : BaseFragment<FragmentProfileSettingsBinding>(
    FragmentProfileSettingsBinding::inflate
) {
    private val shareViewModel: ShareViewModel by activityViewModels()
    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.tvChange.setOnClickListener {
            findNavController().navigate(R.id.action_profileSettingsFragment_to_changePasswordFragment)
        }
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setUpView() {
        super.setUpView()
        getInfoUserFirebase()
    }

    private fun getInfoUserFirebase() {
        val user = Firebase.auth.currentUser
        user?.let {
            val userName = it.displayName
            val email = it.email
            binding.edtFullName.setText(userName)
            binding.edtEmail.setText(email)
        }
        shareViewModel.password.observe(viewLifecycleOwner) {
            binding.edtPassword.setText(it)
        }
    }
}