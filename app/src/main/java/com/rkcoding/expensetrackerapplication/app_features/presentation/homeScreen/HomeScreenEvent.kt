package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen

import com.rkcoding.expensetrackerapplication.utils.TabButton

sealed class HomeScreenEvent {

    data class OnTabValueChange(val tabButton: TabButton): HomeScreenEvent()

}