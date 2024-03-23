package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import com.rkcoding.expensetrackerapplication.ui.theme.purple

@Composable
fun LoginScreen(
    navController: NavController
) {

    // check password visiblity
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Welcome Back !",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Sign in to continue...",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(80.dp))

            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = {
                    Text(text = "Enter Email")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "person image"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = {
                    Text(text = "Enter Password")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "person image"
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible}
                    ) {
                       Icon(
                           imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                           contentDescription = "visible icon image"
                       )
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None
                                         else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
//                    navController.navigate(Screen.LogInScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            ) {
                Text(
                    text = "LOGIN",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Divider(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "or", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Divider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
//                    navController.navigate(Screen.SignUpScreen.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = purple
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = purple
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            ) {
                Text(
                    text = "LOGIN WITH GOOGLE",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "New User?.. ",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Signup Here",
                    fontWeight = FontWeight.Bold,
                    color = purple,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.SignUpScreen.route)
                    }
                )
            }

            
        }
    }

}


@Preview
@Composable
fun HEllo() {
    LoginScreen(navController = rememberNavController())
}

