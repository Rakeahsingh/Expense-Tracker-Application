package com.rkcoding.expensetrackerapplication.utils

import androidx.compose.ui.graphics.Color
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.ui.theme.GreenAlpha700
import com.rkcoding.expensetrackerapplication.ui.theme.healthBg
import com.rkcoding.expensetrackerapplication.ui.theme.subBg

enum class Account(
    val title: String,
    val iconRes: Int,
    val backgroundColor: Color
) {

    CASH("Cash", R.drawable.cash, GreenAlpha700),
    BANK("Bank", R.drawable.bank, subBg),
    CARD("Card", R.drawable.credit_card, healthBg)

}