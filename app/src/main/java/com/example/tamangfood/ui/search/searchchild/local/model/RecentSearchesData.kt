package com.example.tamangfood.ui.search.searchchild.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class RecentSearchesData(var name:String?=""){@PrimaryKey(autoGenerate = true) var id :Int?=0}
