package com.example.tamangfood.ui.signup

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config.showProgressBar
import com.example.tamangfood.databinding.FragmentSignUpBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
) {
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setSafeOnClickListener {
            val email = binding.edtEmail.text.toString()
            val username = binding.edtFullName.text.toString()
            val password = binding.edtPassword.text.toString()
            signUpWithEmailUsernamePassword(email, username, password)
        }
    }

    private fun signUpWithEmailUsernamePassword(email: String, username: String, password: String) {
        showProgressBar(binding.progressBar, true)
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = userProfileChangeRequest {
                        displayName = username
                    }
                    user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                        if (updateTask.isSuccessful) {
                            showProgressBar(binding.progressBar, false)
                            findNavController().navigateUp()
                        } else {
                            //do nothing
                            showProgressBar(binding.progressBar, false)
                        }
                    }
                } else {
                    //do nothing
                    showProgressBar(binding.progressBar, false)
                }
            }
    }
}