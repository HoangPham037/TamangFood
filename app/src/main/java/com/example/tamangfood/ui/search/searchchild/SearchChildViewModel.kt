package com.example.tamangfood.ui.search.searchchild

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tamangfood.ui.search.searchchild.model.RecentSearchesData

class SearchChildViewModel: ViewModel() {
    private val searchData = MutableLiveData<List<RecentSearchesData>>()

    fun getSearchResult(): LiveData<List<RecentSearchesData>> {
        return searchData
    }

    fun searchData(query: String) {
        val searchResult = retrieveSearchResultFromDatabase(query)
        searchData.value = searchResult
    }

    private fun retrieveSearchResultFromDatabase(query: String): List<RecentSearchesData> {
        val dataList = listOf(
            RecentSearchesData("Item 1"),
            RecentSearchesData("Galdan"),
            RecentSearchesData("Pizza hurt"),
            RecentSearchesData("Caffe house"),
            RecentSearchesData("Item 3"),
        )

        return dataList.filter { it.name.contains(query, ignoreCase = true) }
    }
}