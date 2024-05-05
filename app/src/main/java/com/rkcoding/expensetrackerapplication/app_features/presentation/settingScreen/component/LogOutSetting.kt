package com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.identity.Identity
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.component.GoogleAuthUiClient
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import com.rkcoding.expensetrackerapplication.ui.theme.Red500
import kotlinx.coroutines.launch

@Composable
fun LogOutSetting(
    navController: NavController
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // google Auth Client
    val googleAuthClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    val userInfoData = googleAuthClient.getSignedInUser()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {
                scope.launch {
                    googleAuthClient.sinOut()
                    Toast
                        .makeText(
                            context,
                            "SignOut Successfully...",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    navController.navigate(Screen.MainScreen.route)
                }
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = "log out",
                tint = Red500
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "LOGOUT",
            color = Red500,
            fontWeight = FontWeight.Bold
        )



    }

}