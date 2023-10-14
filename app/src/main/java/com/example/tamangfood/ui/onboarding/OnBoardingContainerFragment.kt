package com.example.tamangfood.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tamangfood.R
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentOnBoardingContainerBinding


class OnBoardingContainerFragment : BaseFragment<FragmentOnBoardingContainerBinding>(
    FragmentOnBoardingContainerBinding::inflate
) {

    private var itemsList = ArrayList<OnboardingData>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        itemsList = getItems()
        binding.pager.adapter = PagerAdapter(itemsList)
        binding.wormDot.setViewPager2(binding.pager)
        binding.pager.registerOnPageChangeCallback(pageChangeCallBack)

    }

    private var pageChangeCallBack: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvBtnGetStart.setOnClickListener {
                    val currentItemPosition = binding.pager.currentItem
                    Log.d("Hoang.pv@extremevn.com", "currentItemPosition: $currentItemPosition");
                    if (currentItemPosition == itemsList.size - 1) {
                        findNavController().navigate(R.id.action_onBoardingContainerFragment_to_signInFragment)
                    }
                    binding.pager.setCurrentItem(currentItemPosition + 1, true)
                }
            }
        }

    private fun getItems(): ArrayList<OnboardingData> {
        val items = ArrayList<OnboardingData>()
        items.add(
            OnboardingData(
                resources.getString(R.string.title_walkthrough_1),
                resources.getString(R.string.description_walkthrough_1),
                R.drawable.ic_illustration
            )
        )

        items.add(
            OnboardingData(
                resources.getString(R.string.title_walkthrough_2),
                resources.getString(R.string.description_walkthrough_2),
                R.drawable.lllustrations_two
            )
        )
        items.add(
            OnboardingData(
                resources.getString(R.string.title_walkthrough_3),
                resources.getString(R.string.description_walkthrough_3),
                R.drawable.ic_illustrations_three
            )
        )

        return items
    }

}