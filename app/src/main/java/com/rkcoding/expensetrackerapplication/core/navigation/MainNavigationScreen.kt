package com.rkcoding.expensetrackerapplication.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.rkcoding.expensetrackerapplication.app_features.presentation.component.BottomNavBar
import com.rkcoding.expensetrackerapplication.app_features.presentation.component.provideBottomNavItem

@Composable
fun MainNavigationScreen(
    firebaseAuth: FirebaseAuth
) {

    val hasUser = firebaseAuth.currentUser?.uid

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    val rootDestination = listOf(
        Screen.HomeScreen.route,
        Screen.TransactionChartScreen.route,
        Screen.AccountScreen.route,
        Screen.SettingScreen.route
    )

    val bottomNavState = remember { mutableStateOf(true) }

    val navBackEntity by navController.currentBackStackEntryAsState()

    bottomNavState.value = rootDestination.contains(navBackEntity?.destination?.route)

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            AnimatedVisibility(
                visible = bottomNavState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomNavBar(
                    navController = navController,
                    items = provideBottomNavItem() ,
                    onItemClick = {
                        navController.navigate(it.route){
                            popUpTo(navController.graph.id)
                            launchSingleTop = true
                            restoreState = true
                        }

                    }
                )

            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding())
        ) {

            MainNavigation(
                firebaseAuth = firebaseAuth,
                navController = navController,
                startDestination = if (hasUser != null) Screen.HomeScreen.route else Screen.MainScreen.route
            )

        }

    }

}