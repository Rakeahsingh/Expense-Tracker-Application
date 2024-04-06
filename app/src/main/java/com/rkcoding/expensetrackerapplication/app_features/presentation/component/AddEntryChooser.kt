package com.rkcoding.expensetrackerapplication.app_features.presentation.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerticalAlignBottom
import androidx.compose.material.icons.filled.VerticalAlignTop
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import com.rkcoding.expensetrackerapplication.ui.theme.expenseGradient
import com.rkcoding.expensetrackerapplication.ui.theme.incomeGradient
import kotlinx.coroutines.launch
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEntryChooser(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    navController: NavController
) {

    val scope = rememberCoroutineScope()
    val progress = bottomSheetScaffoldState.bottomSheetState.requireOffset()

    val expendedRotation by animateFloatAsState(
        targetValue = -360 * progress,
        label = "expended icon",
        animationSpec = spring(dampingRatio = 0.75f, stiffness = Spring.StiffnessLow)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            IconButton(
                onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                    navController.navigate("${Screen.AddTransactionScreen.route}/0")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.VerticalAlignBottom,
                    contentDescription = "income icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(45.dp, 45.dp)
                        .rotate(expendedRotation)
                        .background(incomeGradient, CircleShape)
                        .padding(8.dp)
                )
            }

            Text(
                text = "Add Income",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp)
            )

        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            IconButton(
                onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                    navController.navigate("${Screen.AddTransactionScreen.route}/1")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.VerticalAlignTop,
                    contentDescription = "expense icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(45.dp, 45.dp)
                        .rotate(expendedRotation)
                        .background(expenseGradient, CircleShape)
                        .padding(8.dp)
                )
            }

            Text(
                text = "Add Expense",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp)
            )

        }



    }

}