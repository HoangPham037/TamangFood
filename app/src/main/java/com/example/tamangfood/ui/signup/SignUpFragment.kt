package com.example.tamangfood.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.common.Config.showHidePassword
import com.example.tamangfood.common.Config.showProgressBar
import com.example.tamangfood.databinding.FragmentSignUpBinding
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.extensions.showLongLengthToast
import com.example.tamangfood.ui.signin.UsersData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
) {
    private lateinit var auth: FirebaseAuth
    private val rootRef = FirebaseFirestore.getInstance()
    private var uid = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUp.setSafeOnClickListener {
            val email = binding.edtEmail.text.toString()
            val username = binding.edtFullName.text.toString()
            val password = binding.edtPassword.text.toString()
            signUpWithEmailUsernamePassword(email, username, password)
        }
        binding.imgVisibilityOff.setSafeOnClickListener {
            showHidePassword(binding.imgVisibilityOff, binding.edtPassword, requireContext())
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
                    val users = UsersData(username, email, password)
                    val userRef = user?.let { rootRef.collection("users").document(it.uid) }
                    userRef?.set(users)
                        ?.addOnSuccessListener {
                            Timber.tag("success").d("success")
                        }
                        ?.addOnFailureListener {
                            Timber.tag("error").d("error")
                        }
                } else {
                    //do nothing
                    showProgressBar(binding.progressBar, false)
                }
            }
    }
}