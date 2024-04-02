package com.rkcoding.expensetrackerapplication.app_features.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rkcoding.expensetrackerapplication.core.navigation.Screen


data class BottomBarItemHolder(
    val title: String,
    val icon: ImageVector,
    val route: String
)

    fun provideBottomNavItem() = listOf(
        BottomBarItemHolder(
            "Home",
            Icons.Default.Home,
            Screen.HomeScreen.route
        ),

        BottomBarItemHolder(
            "Charts",
            Icons.Default.BarChart,
            Screen.TransactionChartScreen.route
        ),

        BottomBarItemHolder(
            "Accounts",
        Icons.Default.AccountBalanceWallet,
        Screen.AccountScreen.route
        ),

        BottomBarItemHolder(
            "Setting",
            Icons.Default.Settings,
            Screen.SettingScreen.route
        )

    )




