package com.rkcoding.expensetrackerapplication.app_features.domain.model



data class UserInfo(
    val data: UserInfoData?,
    val errorMessage: String?
)


data class UserInfoData(
    val userId: String,
    val userName: String?,
    val userEmail: String?,
    val userPassword: String?,
    val userImage: String?
)
