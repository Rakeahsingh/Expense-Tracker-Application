package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen.component

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.ui.theme.Amber500
import com.rkcoding.expensetrackerapplication.ui.theme.DeepPurple300
import com.rkcoding.expensetrackerapplication.ui.theme.Green200
import com.rkcoding.expensetrackerapplication.ui.theme.GreenAlpha700
import com.rkcoding.expensetrackerapplication.ui.theme.Purple80
import com.rkcoding.expensetrackerapplication.ui.theme.Red200
import com.rkcoding.expensetrackerapplication.ui.theme.Red500
import com.rkcoding.expensetrackerapplication.ui.theme.giftBg
import kotlin.math.roundToInt


@Composable
fun BarChartItem() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.TopCenter
    ){

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            BarCharts(
                inputList = listOf(
                    BarChatInput(28, "Kotlin", Red500),
                    BarChatInput(20, "java", Red200),
                    BarChatInput(15, "php", GreenAlpha700),
                    BarChatInput(12, "go", Green200),
                    BarChatInput(8, "java script", Color.Gray),
                    BarChatInput(32, "python", DeepPurple300),
                    BarChatInput(5, "c", Purple80),
                    BarChatInput(50, "c++", Amber500),
                    BarChatInput(33, ".net", giftBg)
                ) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            )


        }

    }


}

@Composable
fun BarCharts(
    inputList: List<BarChatInput>,
    modifier: Modifier = Modifier,
//    showDescription: Boolean
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val listSum by remember {
            mutableStateOf(inputList.sumOf { it.value })
        }

        inputList.forEach { input ->
            val percentage = input.value/listSum.toFloat()
            Bar(
                modifier = Modifier
                    .height(120.dp * percentage * inputList.size)
                    .width(40.dp),
                primaryColor = input.color,
                percentage = percentage,
                description = input.description,
//                showDescription = showDescription
            )
        }

    }

}


@Composable
fun Bar(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    percentage: Float,
    description: String,
//    showDescription: Boolean
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val width = size.width
            val height = size.height
            val barWidth = width/5*3
            val barHeight = height/8*7
            val barHeight3DPart = height-barHeight
            val barWidth3DPart = (width-barWidth) * (height*0.002f)

            var path = Path().apply {
                moveTo(0f, height)
                lineTo(barWidth, height)
                lineTo(barWidth, height-barHeight)
                lineTo(0f, height-barHeight)
            }
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    listOf(Color.Gray, primaryColor)
                )
            )

            path = Path().apply {
                moveTo(barWidth, height-barHeight)
                lineTo(barWidth3DPart+barWidth, 0f)
                lineTo(barWidth3DPart+barWidth, barHeight)
                lineTo(barWidth, height)
                close()
            }
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    listOf(primaryColor, Color.Gray)
                )
            )

            path = Path().apply {
                moveTo(0f, barHeight3DPart)
                lineTo(barWidth, barHeight3DPart)
                lineTo(barWidth+barWidth3DPart, 0f)
                lineTo(barWidth3DPart, 0f)
                close()
            }
            drawPath(
                path = path,
                brush = Brush.linearGradient(
                    listOf(Color.Gray, Amber500)
                )
            )

            drawContext.canvas.nativeCanvas.apply{
                drawText(
                    "${(percentage*100).roundToInt()} %",
                    barWidth/5f,
                    height + 30f,
                    Paint().apply {
                        color = Color.Gray.toArgb()
                        textSize = 11.dp.toPx()
                        isFakeBoldText = true
                    }
                )
            }

            drawContext.canvas.nativeCanvas.apply{
                rotate(-50f, pivot = Offset(barWidth3DPart-20, 0f)){
                    drawText(
                        description,
                        barWidth/2f,
                        0f,
                        Paint().apply {
                            color = Color.Gray.toArgb()
                            textSize = 14.dp.toPx()
                            isFakeBoldText = true
                        }
                    )
                }

            }

        }

    }

}


data class BarChatInput(
    val value: Int,
    val description: String,
    val color: Color
)