package com.rkcoding.expensetrackerapplication.core

import androidx.compose.material3.SnackbarDuration

sealed class UiEvent {

    data class ShowSnackBar(
        val message: String,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ): UiEvent()

    data object NavigateTo: UiEvent()

}