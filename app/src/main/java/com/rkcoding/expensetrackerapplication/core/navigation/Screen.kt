package com.rkcoding.expensetrackerapplication.core.navigation

sealed class Screen(val route: String){

    data object MainScreen: Screen("main screen")

    data object LogInScreen: Screen("login screen")

    data object SignUpScreen: Screen("signup screen")

    data object WelcomeScreen: Screen("welcome_screen")

    data object HomeScreen: Screen("home_screen")

    data object AddTransactionScreen: Screen("add_transaction_screen")

    data object TransactionChartScreen: Screen("transaction_chart_screen")

    data object AccountScreen: Screen("account_screen")

    data object AccountDetailScreen: Screen("account_detail_screen")

    data object SettingScreen: Screen("setting_screen")

}
