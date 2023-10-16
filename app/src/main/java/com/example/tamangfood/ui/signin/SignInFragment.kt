package com.example.tamangfood.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.layoutBtnSignIn.setOnClickListener {
//            val email = binding.edtEmail.text.toString()
//            val password = binding.edtPassword.text.toString()
//            signInWithEmailUsernamePassword(email, password)
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }
    }

    private fun signInWithEmailUsernamePassword(email: String, password: String) {
        showProgress(true)
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showProgress(false)
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // If sign in fails, display a message to the user.
                    showProgress(false)
                }
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