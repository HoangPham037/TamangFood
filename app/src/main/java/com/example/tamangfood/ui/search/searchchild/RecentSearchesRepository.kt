package com.example.tamangfood.ui.search.searchchild

import com.example.tamangfood.ui.search.searchchild.local.model.RecentSearchesData
import com.example.tamangfood.ui.search.searchchild.local.dao.RecentSearchesDao
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecentSearchesRepository @Inject constructor (private val recentSearchesDao: RecentSearchesDao) {
    suspend fun getAllSearches(): List<RecentSearchesData> {
        return withContext(Dispatchers.IO) {
            recentSearchesDao.getAllSearches()
        }
    }

    suspend fun insertSearch(search: RecentSearchesData) = withContext(Dispatchers.IO) {
        recentSearchesDao.insertSearch(search)
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        recentSearchesDao.deleteAll()
    }

}
