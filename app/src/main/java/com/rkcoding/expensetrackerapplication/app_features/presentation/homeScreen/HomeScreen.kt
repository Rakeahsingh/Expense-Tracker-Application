package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Segment
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VerticalAlignBottom
import androidx.compose.material.icons.filled.VerticalAlignTop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.identity.Identity
import com.rkcoding.expensetrackerapplication.app_features.presentation.component.AddEntryChooser
import com.rkcoding.expensetrackerapplication.app_features.presentation.component.PlaceHolder
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component.DropDownMenuItem
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component.TabButton
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component.TransactionItem
import com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.component.GoogleAuthUiClient
import com.rkcoding.expensetrackerapplication.core.UiEvent
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
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
    val userInfoData = googleAuthClient.getSignedInUser()

    // bottom sheet state
    val bottomSheetState = rememberModalBottomSheetState()
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    val isTabButtonShow by remember { mutableStateOf(false) }

    val scaffoldState = remember {
        SnackbarHostState()
    }


    AddEntryChooser(
        sheetState = bottomSheetState,
        navController = navController,
        isOpen = isBottomSheetOpen,
        onDismiss = { isBottomSheetOpen = false }
    )


    var isDropDownMenuShow by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.NavigateTo -> {
                    navController.navigate(event.route)
                }
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.showSnackbar(
                        message = event.message,
                        duration = event.duration
                    )
                }
            }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = scaffoldState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        bottomSheetState.show()
                        isBottomSheetOpen = true
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(2.dp),
                shape = CircleShape,

                ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add transaction",
                    tint = MaterialTheme.colorScheme.background
                )
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.Center
    ) { paddingValues ->

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
                                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
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

                                    )
                                }else{
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "profile Image",
                                        modifier = Modifier
                                            .size(40.dp)
                                            .clip(CircleShape)
                                            .border(1.dp, Color.Black, CircleShape)
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
                                    onClick = {
//                                        isTabButtonShow = !isTabButtonShow
                                        isDropDownMenuShow = true },
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Segment,
                                        contentDescription = "lines image",
                                        tint = Color.White
                                    )
                                }

                                DropDownMenuItem(
                                    isExpended = isDropDownMenuShow,
                                    onDismissRequest = { isDropDownMenuShow = false },
                                    onTodayMenuClick = {
                                        viewModel.fetTodayTransaction()
                                        isDropDownMenuShow = false
                                    },
                                    onMonthlyMenuClick = {
                                        viewModel.fetMonthlyTransaction()
                                        isDropDownMenuShow = false
                                    },
                                    allTransactionClick = {
                                        scope.launch {
                                            viewModel.getTransaction()
                                        }
                                        isDropDownMenuShow = false
                                    }
                                )

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
                            text = "INR ${state.totalBalance}",
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
                            Card(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(12.dp)),
//                                .background(MaterialTheme.colorScheme.onPrimary),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f)
                                )
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
                                            color = Color.White.copy(alpha = 0.5f),
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
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(
                                            text = "${state.totalIncome}",
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                }
                            }

                            // expense box layout
                            Card(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(12.dp)),
//                                .background(Color.White)
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f)
                                )
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
                                            color = Color.White.copy(alpha = 0.5f),
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
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(
                                            text = "${state.totalExpense}",
                                            color = Color.White,
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
                AnimatedVisibility(visible = isTabButtonShow) {
                    TabButton()
                }


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

                if (state.transaction.isEmpty()){
                    PlaceHolder()
                }

                // transactions list
                LazyColumn {
                    items(state.transaction.reversed()) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                }

            }


    }


}


