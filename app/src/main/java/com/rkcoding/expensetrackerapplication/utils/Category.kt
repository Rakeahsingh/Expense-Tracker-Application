package com.rkcoding.expensetrackerapplication.utils

import androidx.compose.ui.graphics.Color
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.ui.theme.businessBg
import com.rkcoding.expensetrackerapplication.ui.theme.clothBg
import com.rkcoding.expensetrackerapplication.ui.theme.electricBg
import com.rkcoding.expensetrackerapplication.ui.theme.food_drink
import com.rkcoding.expensetrackerapplication.ui.theme.gadgetBg
import com.rkcoding.expensetrackerapplication.ui.theme.giftBg
import com.rkcoding.expensetrackerapplication.ui.theme.groceryBg
import com.rkcoding.expensetrackerapplication.ui.theme.healthBg
import com.rkcoding.expensetrackerapplication.ui.theme.homeBg
import com.rkcoding.expensetrackerapplication.ui.theme.leisureBg
import com.rkcoding.expensetrackerapplication.ui.theme.miscBg
import com.rkcoding.expensetrackerapplication.ui.theme.petBg
import com.rkcoding.expensetrackerapplication.ui.theme.schBg
import com.rkcoding.expensetrackerapplication.ui.theme.snackBg
import com.rkcoding.expensetrackerapplication.ui.theme.subBg
import com.rkcoding.expensetrackerapplication.ui.theme.taxiBg
import com.rkcoding.expensetrackerapplication.ui.theme.travelBg
import com.rkcoding.expensetrackerapplication.ui.theme.vehicleBg

enum class Category(
    val title: String,
    val icon: Int,
    val iconBgColor: Color,
    val iconColor: Color = Color.White
) {

    FOOD_DRINK("Food & Drink", R.drawable.food_drink, food_drink, Color.Black),
    CLOTHING("Clothing", R.drawable.clothing, clothBg, Color.Black),
    HOME("Home", R.drawable.home, homeBg, Color.Black),
    HEALTH("Health", R.drawable.health, healthBg),
    SCHOOL("School", R.drawable.school, schBg),
    GROCERY("Grocery", R.drawable.grocery, groceryBg, Color.Black),
    ELECTRICITY("Electricity", R.drawable.electricity, electricBg, Color.Black),
    BUSINESS("Business", R.drawable.bussiness, businessBg, Color.Black),
    VEHICLE("Vehicle", R.drawable.vehicle, vehicleBg),
    TAXI("Taxi", R.drawable.taxi, taxiBg),
    LEISURE("Leisure", R.drawable.leisure, leisureBg, Color.Black),
    GADGET("Gadget", R.drawable.gadget, gadgetBg),
    TRAVEL("Travel", R.drawable.travel, travelBg, Color.Black),
    SUBSCRIPTION("Subscription", R.drawable.sub, subBg),
    PET("Pet", R.drawable.pet, petBg, Color.Black),
    SNACK("Snack", R.drawable.snack, snackBg, Color.Black),
    GIFT("Gift", R.drawable.gift, giftBg, Color.Black),
    MISC("Miscellaneous", R.drawable.misc, miscBg)

}