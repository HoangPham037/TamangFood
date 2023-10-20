package com.example.tamangfood.ui.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config.showProgressBar
import com.example.tamangfood.databinding.FragmentSignInBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {
    private lateinit var auth: FirebaseAuth
    private val shareViewModel: ShareViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignUp.setSafeOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.layoutBtnSignIn.setSafeOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            signInWithEmailUsernamePassword(email, password)
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }
    }

    private fun signInWithEmailUsernamePassword(email: String, password: String) {
        showProgressBar(binding.progressBar, true)
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    shareViewModel.password.value = password
                    showProgressBar(binding.progressBar, false)
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // If sign in fails, display a message to the user.
                    showProgressBar(binding.progressBar, false)
                }
            }
    }
}