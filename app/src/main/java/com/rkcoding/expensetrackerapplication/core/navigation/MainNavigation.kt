package com.rkcoding.expensetrackerapplication.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.rkcoding.expensetrackerapplication.app_features.presentation.accountsDetailScreen.AccountsDetailScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.accountsScreen.AccountsScreen
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.AddTransactionScreen
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
    navController: NavHostController,
    startDestination: String,
    isChecked: Boolean,
    isCheckedValueChange: (Boolean) -> Unit
) {

//    val hasUser = firebaseAuth.currentUser?.uid

//    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = startDestination
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

        composable(
            route = "${Screen.AddTransactionScreen.route}/{tag}?trxId={trxId}&trxPos={trxPos}&trxStatus={trxStatus}",
            arguments = listOf(
                navArgument("tag"){
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("trxId"){
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ){
            AddTransactionScreen(
                transactionTag = it.arguments?.getInt("tag"),
                transactionId = it.arguments?.getString("trxId"),
                navController = navController
            )
        }

        composable(Screen.TransactionChartScreen.route){
            TransactionChartScreen()
        }

        composable(Screen.AccountScreen.route){
            AccountsScreen(
                navController = navController
            )
        }

        composable(
            route = "${Screen.AccountDetailScreen.route}/{accountType}",
            arguments = listOf(
                navArgument("accountType"){
                    type = NavType.StringType
                    defaultValue = "cash"
                    nullable = true
                }
            )
        ){
            val accountName = it.arguments?.getString("accountType")
            AccountsDetailScreen(
                accountName = accountName,
                navController = navController
            )
        }

        composable(Screen.SettingScreen.route){
            SettingScreen(
                isChecked = isChecked,
                isCheckedValueChange ={
                    isCheckedValueChange(it)
                },
                navController = navController
            )
        }

    }
    
}