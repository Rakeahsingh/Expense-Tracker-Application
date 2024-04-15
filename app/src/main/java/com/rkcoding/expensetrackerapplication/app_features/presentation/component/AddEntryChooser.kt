package com.rkcoding.expensetrackerapplication.app_features.presentation.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerticalAlignBottom
import androidx.compose.material.icons.filled.VerticalAlignTop
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
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

@OptIn( ExperimentalMaterial3Api::class)
@Composable
fun AddEntryChooser(
    sheetState: SheetState,
    navController: NavController,
    sheetTitle: String = "Add Transaction Type",
    isOpen: Boolean,
    onDismiss: () -> Unit,
) {

    val scope = rememberCoroutineScope()
    val progress = sheetState.currentValue

    val expendedRotation by animateFloatAsState(
        targetValue = if (progress == SheetValue.Expanded) 180f else 0f,
        label = "expended icon",
        animationSpec = spring(dampingRatio = 0.75f, stiffness = Spring.StiffnessLow)
    )

    if (isOpen) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            dragHandle = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BottomSheetDefaults.DragHandle()
                    Text(text = sheetTitle, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                }
            },
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {

                        IconButton(
                            onClick = {
                                scope.launch { sheetState.hide() }
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
                                scope.launch { sheetState.hide() }
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
        )
    }
}
