package com.rkcoding.expensetrackerapplication.app_features.domain.repository

import android.net.Uri
import com.rkcoding.expensetrackerapplication.app_features.domain.model.UserInfoData

interface UserAuthRepository {

    suspend fun logInUserWithEmailPassword(email: String, password: String): Result<UserInfoData>

    suspend fun registerUserWithEmailPassword(name: String, email: String, password: String, profileImage: Uri?): Result<String>

}