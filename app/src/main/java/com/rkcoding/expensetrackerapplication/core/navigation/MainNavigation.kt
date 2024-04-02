package com.rkcoding.expensetrackerapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.rkcoding.expensetrackerapplication.app_features.presentation.accountsScreen.AccountsScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.HomeScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen.SettingScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen.TransactionChartScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen.LoginScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.main_Screen.MainScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.signup_screen.SignUpScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.welcomeScreen.CurrencyScreen

@Composable
fun MainNavigation(
    firebaseAuth: FirebaseAuth,
    navController: NavHostController
) {

    val hasUser = firebaseAuth.currentUser?.uid

//    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (hasUser != null) Screen.HomeScreen.route else Screen.MainScreen.route
    ){
        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

        composable(Screen.LogInScreen.route){
            LoginScreen(
                navController = navController
            )
        }

        composable(Screen.SignUpScreen.route){
            SignUpScreen(navController = navController)
        }

        composable(Screen.WelcomeScreen.route){
            CurrencyScreen()
        }

        composable(Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }

        composable(Screen.TransactionChartScreen.route){
            TransactionChartScreen()
        }

        composable(Screen.AccountScreen.route){
            AccountsScreen()
        }

        composable(Screen.SettingScreen.route){
            SettingScreen()
        }

    }
    
}