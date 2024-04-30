package com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen.component.ScreenThemeChanger

@Composable
fun SettingScreen(
    isChecked: Boolean,
    isCheckedValueChange: (Boolean) -> Unit
) {

//    var isChecked by remember {
//        mutableStateOf(false)
//    }

    ScreenThemeChanger(
        isChecked = isChecked,
        isCheckedValueChange = {
            isCheckedValueChange(it)
        }
    )

}