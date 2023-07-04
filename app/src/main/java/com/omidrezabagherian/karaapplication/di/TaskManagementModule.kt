package com.omidrezabagherian.karaapplication.di

import android.content.Context
import androidx.room.Room
import com.omidrezabagherian.karaapplication.data.TaskManagementRepository
import com.omidrezabagherian.karaapplication.data.local.LocalDataSource
import com.omidrezabagherian.karaapplication.data.local.TaskManagementDao
import com.omidrezabagherian.karaapplication.data.local.TaskManagementDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskManagementModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TaskManagementDatabase =
        Room.databaseBuilder(
            context,
            TaskManagementDatabase::class.java,
            "TaskManagement"
        ).build()

    @Singleton
    @Provides
    fun provideDao(database: TaskManagementDatabase): TaskManagementDao = database.taskManagementDao()

    @Singleton
    @Provides
    fun provideTaskManagementRepository(
        localDataSource: LocalDataSource
    ):TaskManagementRepository = TaskManagementRepository(localDataSource)
}

