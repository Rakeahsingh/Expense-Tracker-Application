package com.rkcoding.expensetrackerapplication.core.navigation

sealed class Screen(val route: String){

    data object MainScreen: Screen("main screen")

    data object LogInScreen: Screen("login screen")

    data object SignUpScreen: Screen("signup screen")

}
