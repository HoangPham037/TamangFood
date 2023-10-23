package com.example.tamangfood.ui.search.searchchild.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tamangfood.ui.search.searchchild.local.model.RecentSearchesData

@Dao
interface RecentSearchesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(search: RecentSearchesData)
    @Query("SELECT * FROM search_history")
    fun getAllSearches(): List<RecentSearchesData>
}