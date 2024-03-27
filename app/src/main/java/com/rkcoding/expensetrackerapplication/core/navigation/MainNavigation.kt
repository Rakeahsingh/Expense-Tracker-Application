package com.rkcoding.expensetrackerapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.HomeScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen.LoginScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.main_Screen.MainScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.signup_screen.SignUpScreen

@Composable
fun MainNavigation(
    firebaseAuth: FirebaseAuth
) {

    val hasUser = firebaseAuth.currentUser?.uid

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (hasUser != null) Screen.HomeScreen.route else Screen.MainScreen.route
    ){
        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(Screen.LogInScreen.route){
            LoginScreen(navController = navController)
        }

        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController = navController)
        }

        composable(Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }

    }
    
}