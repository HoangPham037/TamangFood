package com.example.tamangfood.ui.singlerestaurent

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentSingleRestaurantBinding
import com.example.tamangfood.ui.homepage.adapter.PagerAdapter
import com.example.tamangfood.ui.singlerestaurent.adapter.FeaturedItemAdapter
import com.example.tamangfood.ui.singlerestaurent.adapter.TypeFoodVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SingleRestaurantFragment : BaseFragment<FragmentSingleRestaurantBinding>(
    FragmentSingleRestaurantBinding::inflate
) {
    private val shareViewModel: ShareViewModel by activityViewModels()
    private lateinit var featuredItemAdapter: FeaturedItemAdapter
    private lateinit var typeFoodVPAdapter: TypeFoodVPAdapter
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (Build.VERSION.SDK_INT >= 19) {
//            activity?.window?.decorView?.systemUiVisibility =
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        }
//        if (Build.VERSION.SDK_INT >= 21) {
//            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
//            activity?.window?.statusBarColor = Color.TRANSPARENT
//        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = activity?.window
        val winParams = win?.attributes
        if (on) {
            winParams?.flags = winParams?.flags?.or(bits)
        } else {
            winParams?.flags = winParams?.flags?.and(bits.inv())
        }
        win?.attributes = winParams
    }

    override fun observerData() {
        super.observerData()

        shareViewModel.selectRestaurant.observe(viewLifecycleOwner) { restaurant ->

            featuredItemAdapter = FeaturedItemAdapter(restaurant.listProduct)
            binding.rcFeaturedItem.adapter = featuredItemAdapter
            pagerAdapter = PagerAdapter(restaurant.listPage)
            binding.viewPagerRestaurant.adapter = pagerAdapter
            restaurant.listPage?.let { setIndicator(binding.indicatorLayout, it.size) }
            setCurrentIndicator(0, binding.indicatorLayout)
            binding.viewPagerRestaurant.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position, binding.indicatorLayout)
                }
            })
            binding.topText.tvNameRestaurant.text = restaurant.name
            val listType = restaurant.listType ?: emptyList()
            val topTextBinding = binding.topText
            val textType = listOf(
                topTextBinding.tvCurrency,
                topTextBinding.tvTypeOne,
                topTextBinding.tvTypeTwo, topTextBinding.tvCategory
            )
            textType.forEachIndexed { index, textView ->
                textView.text = listType[index]
            }
        }
    }

    private fun setIndicator(view: ViewGroup, pageSize: Int) {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 0 until pageSize) {
            val imageView = ImageView(context)
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(), R.drawable.indicator_inactive
                )
            )
            imageView.layoutParams = layoutParams
            view.addView(imageView)
        }
    }

    private fun setCurrentIndicator(index: Int, view: ViewGroup) {
        val childCount = view.childCount
        for (i in 0 until childCount) {
            val image = view.getChildAt(i) as ImageView
            if (i == index) {
                image.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.indicator_active
                    )
                )
            } else {
                image.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        typeFoodVPAdapter = TypeFoodVPAdapter(requireActivity())
        binding.viewPager.adapter = typeFoodVPAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, postion ->
            when (postion) {
                0 -> tab.text = "See Food"
                1 -> tab.text = "Appetizers"
            }
        }.attach()
    }

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}