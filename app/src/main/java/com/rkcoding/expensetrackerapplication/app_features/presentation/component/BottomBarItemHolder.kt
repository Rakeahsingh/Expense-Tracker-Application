package com.rkcoding.expensetrackerapplication.app_features.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rkcoding.expensetrackerapplication.core.navigation.Screen


data class BottomBarItemHolder(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: String
)

    fun provideBottomNavItem() = listOf(
        BottomBarItemHolder(
            "Home",
            Icons.Filled.Home,
            Icons.Outlined.Home,
            Screen.HomeScreen.route
        ),

        BottomBarItemHolder(
            "Charts",
            Icons.Filled.BarChart,
            Icons.Outlined.BarChart,
            Screen.TransactionChartScreen.route
        ),

        BottomBarItemHolder(
            "Accounts",
            Icons.Filled.AccountBalanceWallet,
            Icons.Outlined.AccountBalanceWallet,
            Screen.AccountScreen.route
        ),

        BottomBarItemHolder(
            "Setting",
            Icons.Filled.Settings,
            Icons.Outlined.Settings,
            Screen.SettingScreen.route
        )

    )




