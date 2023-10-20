package com.example.tamangfood.ui.payment

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentMyPaymentMethodsBinding
import com.example.tamangfood.extensions.gone
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.extensions.visible
import com.example.tamangfood.ui.addpayment.model.PaymentData
import com.example.tamangfood.ui.payment.adapter.MyPaymentAdapter

class MyPaymentMethodsFragment : BaseFragment<FragmentMyPaymentMethodsBinding>(
    FragmentMyPaymentMethodsBinding::inflate
), IOnClickItemPaymentListener {

    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var paymentAdapter: MyPaymentAdapter
    override fun observerData() {
        super.observerData()
        shareViewModel.addPayment.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.layoutCardEmpty.visible()
                binding.rcMyListPayment.gone()
            }
            val list = ArrayList<PaymentData>()
            it?.let { paymentData ->
                binding.layoutCardEmpty.gone()
                binding.rcMyListPayment.visible()
                list.add(paymentData)
                paymentAdapter = MyPaymentAdapter(list, this)
                binding.rcMyListPayment.adapter = paymentAdapter
            }
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAddCreditCard.setSafeOnClickListener {
            findNavController().navigate(R.id.action_myPaymentMethodsFragment_to_addPaymentFragment)
        }
    }

    override fun onClickItemPayment(paymentData: PaymentData) {

    }
}