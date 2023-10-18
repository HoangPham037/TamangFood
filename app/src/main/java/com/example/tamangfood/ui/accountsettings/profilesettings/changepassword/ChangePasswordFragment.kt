package com.example.tamangfood.ui.accountsettings.profilesettings.changepassword

import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentChangePasswordBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(
    FragmentChangePasswordBinding::inflate
) {
    private val shareViewModel: ShareViewModel by activityViewModels()
    private var password: String?=null
    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgInvisiblePass.setOnClickListener {
            showHidePassword(binding.imgInvisiblePass, binding.edtPassword)
        }
        binding.imgInvisibleNewPass.setOnClickListener {
            showHidePassword(binding.imgInvisibleNewPass, binding.edtNewPass)
        }
        binding.imgInvisibleConfirmPass.setOnClickListener {
            showHidePassword(binding.imgInvisibleConfirmPass, binding.edtConfirmPass)

        }
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.layoutBtnChangePass.setOnClickListener {
            showProgress(true)
            val pass = binding.edtPassword.text.toString().trim()
            val newPass = binding.edtNewPass.text.toString().trim()
            val confirmPass = binding.edtConfirmPass.text.toString().trim()
            validate(pass, newPass, confirmPass)
            if (password != pass) {
                binding.edtPassword.error = "Incorrect password"
            }
            if (newPass != confirmPass){
                binding.edtConfirmPass.error = "password does not match"
            }else {
                val user = Firebase.auth.currentUser
                user!!.updatePassword(confirmPass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "update success", Toast.LENGTH_SHORT).show()
                            showProgress(false)
                            findNavController().navigate(R.id.action_changePasswordFragment_to_signInFragment)
                        }else{
                            Toast.makeText(requireContext(), "update error", Toast.LENGTH_SHORT).show()
                            showProgress(false)
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

    private fun showHidePassword(view: View, edt: View) {
        if ((edt as EditText).transformationMethod is PasswordTransformationMethod) {
            edt.transformationMethod = HideReturnsTransformationMethod.getInstance()
            (view as ImageView).setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_visible
                )
            )
        } else {
            (view as ImageView).setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_invisible
                )
            )
            edt.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }
    private fun showProgress(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}