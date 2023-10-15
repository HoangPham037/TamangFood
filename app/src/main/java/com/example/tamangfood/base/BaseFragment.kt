package com.example.tamangfood.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding>(
    private val bindingLayoutInflater: (inflater: LayoutInflater) -> VB
): Fragment() {
    private var _binding:VB?= null
    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingLayoutInflater.invoke(inflater)
        if (_binding == null) {
            throw java.lang.IllegalArgumentException("Binding cannot be null")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
    }

    open fun observerData(){}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}