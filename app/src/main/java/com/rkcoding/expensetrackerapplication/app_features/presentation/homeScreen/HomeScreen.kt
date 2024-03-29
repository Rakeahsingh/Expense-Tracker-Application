package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Segment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Segment
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.identity.Identity
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.component.GoogleAuthUiClient
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import com.rkcoding.expensetrackerapplication.ui.theme.Green
import com.rkcoding.expensetrackerapplication.ui.theme.food_drink
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
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

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ){

                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, start = 16.dp, end = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (userInfoData != null){

                            if (userInfoData.userImage != null){
                                AsyncImage(
                                    model = userInfoData.userImage,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "user Image",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.White, CircleShape)
                                        .clickable {
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
                                )
                            }else{
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "profile Image",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .border(1.dp, Color.Black, CircleShape)
                                        .clickable {
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
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = "Hello ${userInfoData.userName}",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 18.sp
                                )

                                Text(
                                    text = "Welcome Back!",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White.copy(alpha = 0.5f),
                                    fontSize = 14.sp
                                )

                            }



                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Segment,
                                    contentDescription = "lines image",
                                    tint = Color.White
                                )
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Balance",
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Text(
                        text = "$15,000",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 16.dp, end = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.onPrimary)
                        )

                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.onPrimary)
                        )

                    }



                }


                

                

            }



        }
    }


}


@Preview
@Composable
private fun Hello() {
    HomeScreen(navController = rememberNavController())
}