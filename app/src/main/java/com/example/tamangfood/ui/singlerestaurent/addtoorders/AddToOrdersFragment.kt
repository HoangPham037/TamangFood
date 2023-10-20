package com.example.tamangfood.ui.singlerestaurent.addtoorders

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentAddToOrdersBinding
import com.example.tamangfood.extensions.convertToString
import com.example.tamangfood.extensions.setSafeOnClickListener
import com.example.tamangfood.ui.orders.model.OrderData
import com.example.tamangfood.ui.singlerestaurent.model.Product

class AddToOrdersFragment : BaseFragment<FragmentAddToOrdersBinding>(
    FragmentAddToOrdersBinding::inflate
) {

    private val shareViewModel: ShareViewModel by activityViewModels()
    private val addToOrdersViewModel: AddToOrdersViewModel by viewModels()
    private lateinit var products: Product
    private var qualitys: Int = 0
    private val listOrder = ArrayList<OrderData>()
    override fun observerData() {
        super.observerData()
        shareViewModel.selectProduct.observe(viewLifecycleOwner) { product ->
            products = product
            with(binding) {
                imgProduct.setImageResource(product.img)
                tvNameProduct.text = product.name
                tvDesProduct.text = product.description
                val listType = product.listType
                val textType = listOf(
                    tvCurrency,
                    tvTypeOne,
                    tvTypeTwo, tvTypeFood
                )
                textType.forEachIndexed { index, textView ->
                    textView.text = listType[index]
                }
                addToOrdersViewModel.qualityData.observe(viewLifecycleOwner) { quality ->
                    qualitys = quality
                    if (quality < 10) {
                        tvQuantity.text = quality.convertToString()
                    } else {
                        tvQuantity.text = quality.toString()
                    }

                    binding.tvSumPrice.text =
                        (product.price * quality).convertToString()
                }
            }
        }
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setSafeOnClickListener {
            findNavController().navigateUp()
        }
        binding.imgPlus.setSafeOnClickListener {
            addToOrdersViewModel.plusQuality()
        }
        binding.imgMinus.setSafeOnClickListener {
            addToOrdersViewModel.minusQuality()
        }
        binding.btnAddToOrder.setSafeOnClickListener {
            val orderData = OrderData(products, qualitys, products.price * qualitys)
            listOrder.add(orderData)
            shareViewModel.addToOrder.value = listOrder
            findNavController().navigate(R.id.action_addToOrdersFragment_to_yourOrdersFragment)
        }
    }
}