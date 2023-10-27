package com.example.tamangfood.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config
import com.example.tamangfood.common.Config.showProgressBar
import com.example.tamangfood.databinding.FragmentForgotPasswordBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.extensions.showSmallLengthToast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>(
    FragmentForgotPasswordBinding::inflate
) {

    private lateinit var auth: FirebaseAuth

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.layoutBtnResetPass.setSafeOnClickListener {
            val email = binding.edtSendEmailResetPass.text.toString().trim()
            sendResetPassword(email)
        }
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private fun sendResetPassword(email:String) {
        showProgressBar(binding.progressBar, true)
        auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener{task->
                if (task.isSuccessful) {
                    showProgressBar(binding.progressBar, false)
                    requireContext().showSmallLengthToast("password reset successful")
                    findNavController().navigateUp()
                }else {
                    showProgressBar(binding.progressBar, false)
                    requireContext().showSmallLengthToast("Password reset failed")
                }
            }
            .addOnFailureListener {exception->
                handleException(exception)

            }
    }
    private fun handleException(exception: Exception?): String{
        return when(exception){
            is FirebaseAuthUserCollisionException -> "E-mail address is not available"
            is FirebaseNetworkException -> "No network"
            else -> "Try again later."
        }
    }
}