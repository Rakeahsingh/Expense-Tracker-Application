package com.rkcoding.expensetrackerapplication.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.rkcoding.expensetrackerapplication.app_features.data.repository.FirebaseTransactionRepositoryImpl
import com.rkcoding.expensetrackerapplication.app_features.data.repository.UserAuthRepositoryImpl
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.UserAuthRepository
import com.rkcoding.expensetrackerapplication.app_features.domain.use_case.GetAccountDetails
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
    fun provideFireStoreInstance(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFireStoreRepository(
        firebaseAuth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): FirebaseTransactionRepository{
        return FirebaseTransactionRepositoryImpl(firebaseAuth, fireStore)
    }

    @Provides
    @Singleton
    fun provideUserAuthRepository(firebaseAuth: FirebaseAuth): UserAuthRepository{
        return UserAuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: FirebaseTransactionRepository): GetAccountDetails{
        return GetAccountDetails(repository)
    }

}