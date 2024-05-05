package com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen.component.LogOutSetting
import com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen.component.PrivacySetting
import com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen.component.RateUsSetting
import com.rkcoding.expensetrackerapplication.app_features.presentation.settingScreen.component.ScreenThemeChanger

@Composable
fun SettingScreen(
    isChecked: Boolean,
    isCheckedValueChange: (Boolean) -> Unit,
    navController: NavController
) {

//    var isChecked by remember {
//        mutableStateOf(false)
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Setting",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.W700),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        ScreenThemeChanger(
            isChecked = isChecked,
            isCheckedValueChange = {
                isCheckedValueChange(it)
            }
        )

        // privacy setting
        PrivacySetting()

        // RateUs Setting
        RateUsSetting()

        Spacer(modifier = Modifier.weight(1f))

        LogOutSetting(
            navController = navController
        )


    }



}

