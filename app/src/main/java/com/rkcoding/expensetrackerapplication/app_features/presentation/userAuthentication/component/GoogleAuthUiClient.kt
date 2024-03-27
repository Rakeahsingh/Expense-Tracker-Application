package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.rkcoding.expensetrackerapplication.app_features.domain.model.UserInfo
import com.rkcoding.expensetrackerapplication.app_features.domain.model.UserInfoData
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {

    private val auth = Firebase.auth


    @SuppressLint("SuspiciousIndentation")
    suspend fun signIn(): IntentSender?{
        val result = try {
            oneTapClient.beginSignIn(
                beginSignInRequest()
            ).await()
        }catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
              null
        }
        return result?.pendingIntent?.intentSender
    }


    suspend fun signInWithIntent(intent: Intent): UserInfo{
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {

            val user = auth.signInWithCredential(googleCredential).await().user

            UserInfo(
                data = user?.run {
                    UserInfoData(
                        userId = uid,
                        userName = displayName,
                        userEmail = email,
                        userPassword = "",
                        userImage = photoUrl.toString()
                    )
                },
                errorMessage = null
            )

        }catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            return UserInfo(
                data = null,
                errorMessage = e.message
            )
        }
    }


    fun getSignedInUser(): UserInfoData? = auth.currentUser?.run {
        UserInfoData(
            userId = uid,
            userName = displayName,
            userEmail = email,
            userPassword = "",
            userImage = photoUrl.toString()
        )
    }

    suspend fun sinOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        }catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    private fun beginSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setServerClientId("1065208923378-vm0fd9pjtv6i8fs2furqr2k8o52ddmqj.apps.googleusercontent.com")
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(false)
            .build()
    }

}