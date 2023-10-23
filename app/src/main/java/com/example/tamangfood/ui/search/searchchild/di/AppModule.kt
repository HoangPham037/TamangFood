package com.example.tamangfood.ui.search.searchchild.di

import android.content.Context
import androidx.room.Room
import com.example.tamangfood.ui.search.searchchild.local.dao.RecentSearchesDao
import com.example.tamangfood.ui.search.searchchild.local.db.RecentSearchesDatabase
import com.example.tamangfood.ui.search.searchchild.RecentSearchesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSearchDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecentSearchesDatabase::class.java,
        "search_history"
    ).build()

    @Provides
    fun provideSearchDao(database: RecentSearchesDatabase): RecentSearchesDao {
        return database.recentSearchesDao()
    }

    @Provides
    fun provideSearchHistoryRepository(dao: RecentSearchesDao): RecentSearchesRepository {
        return RecentSearchesRepository(dao)
    }
}