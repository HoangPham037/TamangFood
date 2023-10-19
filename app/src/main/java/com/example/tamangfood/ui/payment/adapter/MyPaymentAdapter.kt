package com.example.tamangfood.ui.payment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tamangfood.databinding.ItemMyPaymentBinding
import com.example.tamangfood.ui.addpayment.model.PaymentData
import com.example.tamangfood.ui.payment.IOnClickItemPaymentListener

class MyPaymentAdapter(private val list: List<PaymentData>,private val listener: IOnClickItemPaymentListener) : RecyclerView.Adapter<MyPaymentAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemMyPaymentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(paymentData: PaymentData) = with(binding) {
            tvPayment.text = paymentData.payment
            tvMyyyy.text = paymentData.date
            cvc.text = paymentData.cvc.toString()
            binding.btnClicked.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMyPaymentBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener.onClickItemPayment(list[position])
        }
    }
}
