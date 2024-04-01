package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.VerticalAlignBottom
import androidx.compose.material.icons.filled.VerticalAlignTop
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.identity.Identity
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component.TabButton
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.component.GoogleAuthUiClient
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import com.rkcoding.expensetrackerapplication.ui.theme.Black
import com.rkcoding.expensetrackerapplication.ui.theme.Green
import com.rkcoding.expensetrackerapplication.ui.theme.food_drink
import com.rkcoding.expensetrackerapplication.ui.theme.purple
import com.rkcoding.expensetrackerapplication.ui.theme.schBg
import com.rkcoding.expensetrackerapplication.ui.theme.subBg
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
                    .background(Black)
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

                            // log in user image
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

                                // login user name
                                Text(
                                    text = "Hello, ${userInfoData.userName}",
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

                    // main balance
                    Text(
                        text = "INR 15,000",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        // income box layout
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                        ){
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.VerticalAlignBottom,
                                        contentDescription = "income icon",
                                        tint = Color.Green
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "Income",
                                        color = Color.Black,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "INR",
                                        color = Color.DarkGray,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "10,000",
                                        color = Color.Gray,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                            }
                        }

                        // expense box layout
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                        ){
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.VerticalAlignTop,
                                        contentDescription = "income icon",
                                        tint = Color.Red
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "Expense",
                                        color = Color.Black,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "INR",
                                        color = Color.DarkGray,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(
                                        text = "10,000",
                                        color = Color.Gray,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                            }
                        }

                    }


                }


            }

            Spacer(modifier = Modifier.height(8.dp))

            // Today and monthly tab button
            TabButton()

            Spacer(modifier = Modifier.height(4.dp))

            // text transaction history
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Transaction History",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "See all",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )

            }

            // dummy transaction

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            ){
                Column (
                    modifier = Modifier.padding(vertical = 8.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp)
                    ) {
                        Text(text = "School")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Bank")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row (
                        modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)){
                            Image(painter = painterResource(id = R.drawable.bank),
                                contentDescription = "bank" )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "College Fess")
                            Text(text = "INR 1,50,000")
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            ){
                Column (
                    modifier = Modifier.padding(vertical = 8.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp)
                    ) {
                        Text(text = "School")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Bank")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)){
                            Image(painter = painterResource(id = R.drawable.bank),
                                contentDescription = "bank" )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "College Fess")
                            Text(text = "INR 1,50,000")
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            ){
                Column (
                    modifier = Modifier.padding(vertical = 8.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp)
                    ) {
                        Text(text = "School")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Bank")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)){
                            Image(painter = painterResource(id = R.drawable.bank),
                                contentDescription = "bank" )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "College Fess")
                            Text(text = "INR 1,50,000")
                        }
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