package com.example.tamangfood.ui.accountsettings.profilesettings.changepassword

import android.text.TextUtils
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config.showHidePassword
import com.example.tamangfood.common.Config.showProgressBar
import com.example.tamangfood.databinding.FragmentChangePasswordBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.extensions.showLongLengthToast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(
    FragmentChangePasswordBinding::inflate
) {
    private val shareViewModel: ShareViewModel by activityViewModels()
    private var password: String? = null
    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgInvisiblePass.setSafeOnClickListener {
            showHidePassword(binding.imgInvisiblePass, binding.edtPassword, requireContext())
        }
        binding.imgInvisibleNewPass.setSafeOnClickListener {
            showHidePassword(binding.imgInvisibleNewPass, binding.edtNewPass, requireContext())
        }
        binding.imgInvisibleConfirmPass.setSafeOnClickListener {
            showHidePassword(
                binding.imgInvisibleConfirmPass,
                binding.edtConfirmPass,
                requireContext()
            )

        }
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }

        binding.layoutBtnChangePass.setSafeOnClickListener {
            showProgressBar(binding.progressBar, true)
            val pass = binding.edtPassword.text.toString().trim()
            val newPass = binding.edtNewPass.text.toString().trim()
            val confirmPass = binding.edtConfirmPass.text.toString().trim()
            validate(pass, newPass, confirmPass)
            if (password != pass) {
                binding.edtPassword.error = "Incorrect password"
            }
            if (newPass != confirmPass) {
                binding.edtConfirmPass.error = "password does not match"
            } else {
                val user = Firebase.auth.currentUser
                user!!.updatePassword(confirmPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            requireContext().showLongLengthToast("update success")
                            showProgressBar(binding.progressBar, false)
                            findNavController().navigate(R.id.action_changePasswordFragment_to_signInFragment)
                        } else {
                            requireContext().showLongLengthToast("update error")
                            showProgressBar(binding.progressBar, false)
                        }
                    }

            }
        }
    }

    override fun observerData() {
        super.observerData()
        shareViewModel.password.observe(viewLifecycleOwner) {
            password = it
        }
    }


    private fun validate(pass: String, newPass: String, confirmPass: String) {

        if (TextUtils.isEmpty(pass)) {
            binding.edtPassword.error = "cannot be left blank"
        }
        if (TextUtils.isEmpty(newPass)) {
            binding.edtPassword.error = "cannot be left blank"
        }
        if (TextUtils.isEmpty(confirmPass)) {
            binding.edtPassword.error = "cannot be left blank"
        }
    }
}