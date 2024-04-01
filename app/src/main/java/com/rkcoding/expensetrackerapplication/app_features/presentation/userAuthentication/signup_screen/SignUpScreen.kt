package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.signup_screen

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.identity.Identity
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.component.GoogleAuthUiClient
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen.LoginEvent
import com.rkcoding.expensetrackerapplication.core.UiEvent
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import com.rkcoding.expensetrackerapplication.ui.theme.purple
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // google Auth Client
    val googleAuthClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    // google login Launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == Activity.RESULT_OK) {
                scope.launch {
                    val signInResult = googleAuthClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onEvent(SignupEvent.OnGoogleButtonClick(signInResult))
                }
            }
        }
    )

    // select image from gallery state
    var selectImageUri by remember { mutableStateOf<Uri?>(null) }
    // select image from gallery
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectImageUri = uri
        }
    )

    // snackBar state
    val snackBarState = remember { SnackbarHostState() }

    // password visible
    var isPasswordVisible by remember { mutableStateOf(false) }

    // confirm password visible
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.NavigateTo -> navController.navigate(event.route)
                is UiEvent.ShowSnackBar -> {
                    snackBarState.showSnackbar(
                        message = event.message,
                        duration = event.duration
                    )
                }
            }
        }
    }

    
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 24.dp),
                text = "Hii!",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Create a new Account...",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                CircularImage(
                    imageUri = selectImageUri,
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.userName,
                onValueChange = { userName ->
                     viewModel.onEvent(SignupEvent.OnNameValueChange(userName))
                },
                label = {
                    Text(text = "Enter Name")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "person image"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = state.userEmail,
                onValueChange = { userEmail ->
                     viewModel.onEvent(SignupEvent.OnEmailValueChange(userEmail))
                },
                label = {
                    Text(text = "Enter Email")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email image"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = state.userPassword,
                onValueChange = { userPassword ->
                     viewModel.onEvent(SignupEvent.OnPasswordValueChange(userPassword))
                },
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
                            imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
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

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = state.userConfirmPassword,
                onValueChange = { userConfirmPassword ->
                     viewModel.onEvent(SignupEvent.OnConfirmPasswordValueChange(userConfirmPassword))
                },
                label = {
                    Text(text = "Enter Confirm Password")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password image"
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible}
                    ) {
                        Icon(
                            imageVector = if (isConfirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "visible icon image"
                        )
                    }
                },
                visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    viewModel.onEvent(SignupEvent.OnSignupButtonClick)
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
                    text = "SIGNUP",
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
                       scope.launch {
                           val sinInIntentSender = googleAuthClient.signIn()
                           launcher.launch(
                               IntentSenderRequest.Builder(
                                   sinInIntentSender ?: return@launch
                               ).build()
                           )
                       }
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
                    text = "Already User?.. ",
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Login Here",
                    fontWeight = FontWeight.Bold,
                    color = purple,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.LogInScreen.route)
                    }
                )
            }
            
        }
    }

}



@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
            .size(120.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = Color.LightGray, shape = CircleShape)
            .clickable { onClick() }
    ){

        if (imageUri != null){

            AsyncImage(
                model = imageUri,
                contentScale = ContentScale.Crop,
                contentDescription ="image uri",
                modifier = Modifier.fillMaxSize()
            )

        }else{
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "person Image",
                modifier = Modifier.fillMaxSize()
            )
        }

    }

}


@Preview
@Composable
fun Hello() {
    SignUpScreen(navController = rememberNavController())
}