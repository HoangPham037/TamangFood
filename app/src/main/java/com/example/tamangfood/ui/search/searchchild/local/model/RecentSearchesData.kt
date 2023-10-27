package com.example.tamangfood.ui.search.searchchild.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class RecentSearchesData(
    @ColumnInfo(name = "name")
    var name: String? = ""
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
