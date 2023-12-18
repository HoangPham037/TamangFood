package com.example.tamangfood.ui.orders.addtoorders

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentAddToOrdersBinding
import com.example.tamangfood.extensions.loadImg
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.orders.model.Cart

class AddToOrdersFragment : BaseFragment<FragmentAddToOrdersBinding>(
    FragmentAddToOrdersBinding::inflate
) {

    private val shareViewModel: ShareViewModel by activityViewModels()
    private val addToOrdersViewModel: AddToOrdersViewModel by viewModels()

    override fun observerData() {
        super.observerData()
        shareViewModel.selectProduct.observe(viewLifecycleOwner) { product ->
            with(binding) {
                requireContext().loadImg(product.imgUrl, imgProduct)
                tvNameProduct.text = product.name
                tvDesProduct.text = product.description

                addToOrdersViewModel.qualityData.observe(viewLifecycleOwner) { quality ->
                    if (quality < 10) {
                        tvQuantity.text = String.format("%d%d", 0, quality)
                    } else {
                        tvQuantity.text = quality.toString()
                    }

                    binding.tvSumPrice.text =
                        String.format("%s%.2f%s", "($", product.price!! * quality, ")")
                    val cart = Cart(product, quality, product.price * quality)


                }
            }
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.imgPlus.setOnClickListener {
            addToOrdersViewModel.plusQuality()
        }
        binding.imgMinus.setOnClickListener {
            addToOrdersViewModel.minusQuality()
        }
        binding.btnAddToOrder.setSafeOnClickListener {

        }
    }
}