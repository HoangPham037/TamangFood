package com.example.tamangfood.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentPaymentMethodBinding
import com.example.tamangfood.extensions.setSafeOnClickListener


class PaymentMethodFragment : BaseFragment<FragmentPaymentMethodBinding>(
    FragmentPaymentMethodBinding::inflate
) {

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgGotoPaypal.setSafeOnClickListener {
            findNavController().navigate(R.id.action_paymentMethodFragment_to_myPaymentMethodsFragment)
        }
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
    }
}