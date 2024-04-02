package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.ui.theme.GreenAlpha700
import com.rkcoding.expensetrackerapplication.ui.theme.LightBlue500
import com.rkcoding.expensetrackerapplication.ui.theme.schBg
import com.rkcoding.expensetrackerapplication.utils.Category

@Composable
fun TransactionItem(
//    transaction: Transaction
) {

//    val category = getCategory(transaction.category)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray.copy(alpha = 0.5f))
    ){
        Column (
            modifier = Modifier.padding(vertical = 8.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp)
            ) {
                Text(
                    text = "School",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(schBg, shape = RoundedCornerShape(24.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Bank",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(LightBlue500, shape = RoundedCornerShape(24.dp))
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.bank),
                    contentDescription = "account type icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .background(Color.DarkGray.copy(alpha = 0.2f), CircleShape)
                        .padding(12.dp)
                    )

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
//                    if (transaction.transactionTitle.isNotBlank()){
                        Text(
                            text = "College Fess",
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
//                    }


                    Text(
                        text = "02 Apr 2024, 10:00AM",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }


                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "+ 1,50,000", color = GreenAlpha700,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }

}

fun getCategory(title: String): Category{
    var result: Category = Category.FOOD_DRINK
    Category.entries.forEach{ category ->
        if (category.title == title){
            result = category
        }
    }
    return result
}