package com.rkcoding.expensetrackerapplication.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.rkcoding.expensetrackerapplication.app_features.data.repository.UserAuthRepositoryImpl
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.UserAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideUserAuthRepository(firebaseAuth: FirebaseAuth): UserAuthRepository{
        return UserAuthRepositoryImpl(firebaseAuth)
    }

}