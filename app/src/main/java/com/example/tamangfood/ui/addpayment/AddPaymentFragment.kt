package com.example.tamangfood.ui.addpayment

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tamangfood.R
import com.example.tamangfood.ShareViewModel
import com.example.tamangfood.base.BaseFragment
import com.example.tamangfood.databinding.FragmentAddPaymentBinding
import com.example.tamangfood.ui.addpayment.model.PaymentData


class AddPaymentFragment : BaseFragment<FragmentAddPaymentBinding>(
    FragmentAddPaymentBinding::inflate
) {
    companion object {
        const val TOTAL_SYMBOLS = 19
        const val TOTAL_DIGITS = 16
        private const val DIVIDER_MODULO = 5
        const val DIVIDER_POSITION = DIVIDER_MODULO - 1
        const val DIVIDER = '-'


    }

    private val shareViewModel: ShareViewModel by activityViewModels()

    override fun setUpOnClickListener() {
        super.setUpOnClickListener()
        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.layoutBtnAddCard.setOnClickListener {
            showProgress(true)
            val paymentNumber = binding.edtPayment.text.toString()
            val date = binding.edtMyyyy.text.toString()
            val cvc = binding.edtCvc.text.toString()
            val paymentData = PaymentData(paymentNumber, date, cvc.toInt())
            shareViewModel.addPayment.value = paymentData
            showProgress(false)
            Toast.makeText(requireContext(), "Add card success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addPaymentFragment_to_homeFragment)

        }
    }
    override fun setUpView() {
        super.setUpView()
        initInputPaymentNumber()
        initInputMYYY()
    }

    private fun initInputPaymentNumber() {
        binding.edtPayment.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }

            override fun afterTextChanged(s: Editable) {
                if (!isInputCorrect(s, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
                    s.replace(
                        0,
                        s.length,
                        buildCorrectString(
                            getDigitArray(s, TOTAL_DIGITS)!!,
                            DIVIDER_POSITION,
                            DIVIDER
                        )
                    );
                }
            }

            private fun isInputCorrect(
                s: Editable,
                totalSymbols: Int,
                dividerModulo: Int,
                divider: Char
            ): Boolean {
                var isCorrect = s.length <= totalSymbols // check size of entered string
                for (i in s.indices) { // check that every element is right
                    isCorrect = if (i > 0 && (i + 1) % dividerModulo == 0) {
                        isCorrect and (divider == s[i])
                    } else {
                        isCorrect and Character.isDigit(s[i])
                    }
                }
                return isCorrect
            }

            private fun buildCorrectString(
                digits: CharArray,
                dividerPosition: Int,
                divider: Char
            ): String? {
                val formatted = StringBuilder()
                for (i in digits.indices) {
                    if (digits[i].code != 0) {
                        formatted.append(digits[i])
                        if (i > 0 && i < digits.size - 1 && (i + 1) % dividerPosition == 0) {
                            formatted.append(divider)
                        }
                    }
                }
                return formatted.toString()
            }

            private fun getDigitArray(s: Editable, size: Int): CharArray? {
                val digits = CharArray(size)
                var index = 0
                var i = 0
                while (i < s.length && index < size) {
                    val current = s[i]
                    if (Character.isDigit(current)) {
                        digits[index] = current
                        index++
                    }
                    i++
                }
                return digits
            }
        })
    }

    private fun initInputMYYY() {
        binding.edtMyyyy.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (start == 1 && start + count == 2 && s?.contains('/') == false) {
                    binding.edtMyyyy.setText("$s/")
                } else if (start == 3 && start - before == 2 && s?.contains('/') == true) {
                    binding.edtMyyyy.setText(s.toString().replace("/", ""))
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // do nothing
            }

        })
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}