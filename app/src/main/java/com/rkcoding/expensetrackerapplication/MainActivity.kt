package com.rkcoding.expensetrackerapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.rkcoding.expensetrackerapplication.core.navigation.MainNavigationScreen
import com.rkcoding.expensetrackerapplication.ui.theme.ExpenseTrackerApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isChecked by mutableStateOf(false)

        setContent {
            ExpenseTrackerApplicationTheme(
                darkTheme = isChecked
            ) {
                // A surface container using the 'background' color from the theme


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    MainNavigation(firebaseAuth = firebaseAuth
                    MainNavigationScreen(
                        firebaseAuth = firebaseAuth,
                        isChecked = isChecked,
                        isCheckedValueChange = { isChecked = !isChecked }
                    )


                }
            }
        }
    }
}

