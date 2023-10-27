package com.example.tamangfood.ui.signin

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config
import com.example.tamangfood.common.Config.showProgressBar
import com.example.tamangfood.databinding.FragmentSignInBinding
import com.example.tamangfood.extensions.getMySharedPreferences
import com.example.tamangfood.extensions.putString
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {
    private lateinit var auth: FirebaseAuth
    private val rootRef = FirebaseFirestore.getInstance()
    private val shareViewModel: ShareViewModel by activityViewModels()
    private val user = FirebaseAuth.getInstance().currentUser

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.tvSignUp.setSafeOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.layoutBtnSignIn.setSafeOnClickListener {
            val imm =
                requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.edtPassword.windowToken, 0)
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            signInWithEmailUsernamePassword(email, password)
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }

        binding.imgVisibilityOff.setSafeOnClickListener {
            Config.showHidePassword(binding.imgVisibilityOff, binding.edtPassword, requireContext())
        }
        binding.tvForgetPassword.setSafeOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
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
                } else {
                    showProgressBar(binding.progressBar, false)
                }
            }
//        if (user == null) {
//
//        }
//        val users = UsersData(email, password)
//        val userRef = rootRef.collection("users")
//        userRef.add(users)
//            .addOnSuccessListener { documentReference ->
//                val sharedPreferences = requireContext().getMySharedPreferences()
//                sharedPreferences.putString("userId", documentReference.id)
//                Timber.tag("SUCCESS").i("add info user success")
//            }
//            .addOnFailureListener {
//                Timber.tag("ERROR").i("add info user failed")
//            }
    }
}