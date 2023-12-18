package com.example.tamangfood.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.homepage.model.Partners
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    private val fireStore = FirebaseFirestore.getInstance()
    val partnersList: MutableLiveData<List<Partners>> by lazy {
        MutableLiveData<List<Partners>>().apply {
            loadPartners()
        }
    }
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    private fun loadPartners() {
        isLoading.value = true
        val partnersRef = fireStore.collection("restaurants")
        val partnersListTemp = mutableListOf<Partners>()

        partnersRef.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val partner = document.toObject(Partners::class.java)
                    partnersListTemp.add(partner)
                }

                partnersList.value = partnersListTemp
                isLoading.value = false
            }
            .addOnFailureListener { exception ->
                isLoading.value = false
            }
    }
}