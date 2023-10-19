package com.example.tamangfood.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentMyPaymentMethodsBinding
import com.example.tamangfood.ui.addpayment.model.PaymentData
import com.example.tamangfood.ui.payment.adapter.MyPaymentAdapter

class MyPaymentMethodsFragment : BaseFragment<FragmentMyPaymentMethodsBinding>(
    FragmentMyPaymentMethodsBinding::inflate
), IOnClickItemPaymentListener {

    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var paymentAdapter : MyPaymentAdapter
    override fun observerData() {
        super.observerData()
        shareViewModel.addPayment.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.layoutCardEmpty.visibility = View.VISIBLE
                binding.rcMyListPayment.visibility = View.GONE
            }
            val list = ArrayList<PaymentData>()
            it?.let {paymentData ->
                binding.layoutCardEmpty.visibility = View.GONE
                binding.rcMyListPayment.visibility = View.VISIBLE
                list.add(paymentData)
                paymentAdapter = MyPaymentAdapter(list,this)
                binding.rcMyListPayment.adapter = paymentAdapter
            }
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAddCreditCard.setOnClickListener {
            Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_myPaymentMethodsFragment_to_addPaymentFragment)
        }
    }

    override fun onClickItemPayment(paymentData: PaymentData) {

    }
}