package com.rkcoding.expensetrackerapplication.app_features.data.repository

import android.net.Uri
import androidx.core.net.toUri
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.UserProfileChangeRequest
import com.rkcoding.expensetrackerapplication.app_features.domain.model.UserInfoData
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.UserAuthRepository
import kotlinx.coroutines.tasks.await

class UserAuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
): UserAuthRepository {
    override suspend fun logInUserWithEmailPassword(
        email: String,
        password: String
    ): Result<UserInfoData> {
        return try {

            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result.user != null){
                val user = result.user!!

                Result.success(
                    UserInfoData(
                        userId = user.uid,
                        userName = user.displayName,
                        userEmail = user.email,
                        userPassword = "",
                        userImage = user.photoUrl
                    )
                )
            }else{
                Result.failure(
                    Exception("Login Failed")
                )
            }


        }catch (e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }catch (e: FirebaseAuthInvalidUserException) {
            Result.failure(Exception("User does not exist"))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(Exception("Invalid Google credentials"))
        } catch (e: FirebaseException) {
            Result.failure(Exception(e.localizedMessage ?: "Google Login Failed"))
        }
    }

    override suspend fun registerUserWithEmailPassword(
        name: String,
        email: String,
        password: String,
        profileImage: Uri?
    ): Result<String> {

        return try {

            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            if (result.user != null){
                val user = result.user!!
                val profileInfoBuilder = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                if (profileImage != null){
                    profileInfoBuilder.photoUri = profileImage
                }
                val profileInfo = profileInfoBuilder.build()

                user.updateProfile(profileInfo).await()

                Result.success(user.uid)
            }else{
                Result.failure(Exception("user is null"))
            }

        }catch (e: Exception){
            e.printStackTrace()
            Result.failure(e)
        } catch (e: FirebaseAuthUserCollisionException) {
            return Result.failure(Exception("Email is already in use"))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            return Result.failure(Exception("Invalid email or password"))
        } catch (e: FirebaseAuthEmailException) {
            return Result.failure(Exception("Invalid email format"))
        }

    }
}