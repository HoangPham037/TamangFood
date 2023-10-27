package com.example.tamangfood.ui.search.searchchild

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tamangfood.ui.search.searchchild.local.model.RecentSearchesData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import kotlinx.coroutines.launch

@HiltViewModel
class RecentSearchesViewModel @Inject constructor(private val recentSearchesRepository: RecentSearchesRepository) :
    ViewModel() {
    private val _recentSearches = MutableLiveData<List<RecentSearchesData>>()
    val recentSearches: LiveData<List<RecentSearchesData>> get() = _recentSearches
    fun fetchRecentSearch() {
        viewModelScope.launch {
            val recentSearches = recentSearchesRepository.getAllSearches()
            _recentSearches.value = recentSearches
        }
    }

    fun insertRecentSearches(searchesData: RecentSearchesData) {
        viewModelScope.launch {
            recentSearchesRepository.insertSearch(searchesData)
        }
    }

    fun deleteAllRecentSearches() {
        viewModelScope.launch {
            recentSearchesRepository.deleteAll()
        }
    }
}