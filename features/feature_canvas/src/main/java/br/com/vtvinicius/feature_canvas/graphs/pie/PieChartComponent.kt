package br.com.vtvinicius.feature_canvas.graphs.pie

import androidx.compose.runtime.Composable
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.vtvinicius.uikit.base.gray
import br.com.vtvinicius.uikit.base.white
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.atan2

@Composable
fun PieChartComponent(
    modifier: Modifier = Modifier,
    radius: Float = 150f,
    innerRadius: Float = 50f,
    transparentWidth: Float = 50f,
    input: List<PieChartInput>,
) {
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var inputList by remember {
        mutableStateOf(input)
    }
    var isCenterTapped by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val tapAngleInDegrees = (-atan2(
                                x = circleCenter.y - offset.y,
                                y = circleCenter.x - offset.x
                            ) * (180f / PI).toFloat() - 90f).mod(360f)
                            val centerClicked = if (tapAngleInDegrees < 90) {
                                offset.x < circleCenter.x + innerRadius.dp.toPx() && offset.y < circleCenter.y + innerRadius.dp.toPx()
                            } else if (tapAngleInDegrees < 180) {
                                offset.x > circleCenter.x - innerRadius.dp.toPx() && offset.y < circleCenter.y + innerRadius.dp.toPx()
                            } else if (tapAngleInDegrees < 270) {
                                offset.x > circleCenter.x - innerRadius.dp.toPx() && offset.y > circleCenter.y - innerRadius.dp.toPx()
                            } else {
                                offset.x < circleCenter.x + innerRadius.dp.toPx() && offset.y > circleCenter.y - innerRadius.dp.toPx()
                            }

                            if (centerClicked) {
                                inputList = inputList.map {
                                    it.copy(isTapped = !isCenterTapped)
                                }
                                isCenterTapped = !isCenterTapped
                            } else {
                                val anglePerValue = 360f / input.sumOf {
                                    it.value
                                }
                                var currAngle = 0f
                                inputList.forEach { pieChartInput ->

                                    currAngle += pieChartInput.value * anglePerValue
                                    if (tapAngleInDegrees < currAngle) {
                                        val description = pieChartInput.description
                                        inputList = inputList.map {
                                            if (description == it.description) {
                                                it.copy(isTapped = !it.isTapped)
                                            } else {
                                                it.copy(isTapped = false)
                                            }
                                        }
                                        return@detectTapGestures
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            val width = size.width
            val height = size.height
            circleCenter = Offset(x = width / 2f, y = height / 2f)

            val totalValue = input.sumOf {
                it.value
            }
            val anglePerValue = 360f / totalValue
            var currentStartAngle = 0f

            inputList.forEach { pieChartInput ->
                val scale = if (pieChartInput.isTapped) 1.1f else 1.0f
                val angleToDraw = pieChartInput.value * anglePerValue
                scale(scale) {
                    drawArc(
                        color = pieChartInput.color,
                        startAngle = currentStartAngle,
                        sweepAngle = angleToDraw,
                        useCenter = true,
                        size = Size(
                            width = radius.dp.toPx() * 2f,
                            height = radius.dp.toPx() * 2f
                        ),
                        topLeft = Offset(
                            (width - radius.dp.toPx() * 2f) / 2f,
                            (height - radius.dp.toPx() * 2f) / 2f
                        )
                    )
                    currentStartAngle += angleToDraw
                }
                var rotateAngle = currentStartAngle - angleToDraw / 2f - 90f
                var factor = 1f
                if (rotateAngle > 90f) {
                    rotateAngle = (rotateAngle + 180).mod(360f)
                    factor = -0.92f
                }

                val percentage = (pieChartInput.value / totalValue.toFloat() * 100).toInt()

                drawContext.canvas.nativeCanvas.apply {
                    if (percentage > 3) {
                        rotate(rotateAngle) {
                            drawText(
                                "$percentage %",
                                circleCenter.x,
                                circleCenter.y + (radius.dp.toPx() - (radius.dp.toPx() - innerRadius.dp.toPx()) / 2f) * factor,
                                Paint().apply {
                                    textSize = 13.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = white.toArgb()
                                }
                            )
                        }
                    }
                }
                if (pieChartInput.isTapped) {
                    val tabRotation = currentStartAngle - angleToDraw - 90f
                    rotate(tabRotation) {
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, radius.dp.toPx() * 1.2f),
                            color = gray,
                            cornerRadius = CornerRadius(15f, 15f)
                        )
                    }
                    rotate(tabRotation + angleToDraw) {
                        drawRoundRect(
                            topLeft = circleCenter,
                            size = Size(12f, radius.dp.toPx() * 1.2f),
                            color = gray,
                            cornerRadius = CornerRadius(15f, 15f)
                        )
                    }
                    rotate(rotateAngle) {
                        drawContext.canvas.nativeCanvas.apply {
                            drawText(
                                "${pieChartInput.description}: ${pieChartInput.value}",
                                circleCenter.x,
                                circleCenter.y + radius.dp.toPx() * 1.3f * factor,
                                Paint().apply {
                                    textSize = 22.sp.toPx()
                                    textAlign = Paint.Align.CENTER
                                    color = white.toArgb()
                                    isFakeBoldText = true
                                }
                            )
                        }
                    }
                }
            }

            if (inputList.first().isTapped) {
                rotate(-90f) {
                    drawRoundRect(
                        topLeft = circleCenter,
                        size = Size(12f, radius.dp.toPx() * 1.2f),
                        color = gray,
                        cornerRadius = CornerRadius(15f, 15f)
                    )
                }
            }
            drawContext.canvas.nativeCanvas.apply {
                drawCircle(
                    circleCenter.x,
                    circleCenter.y,
                    innerRadius.dp.toPx(),
                    Paint().apply {
                        color = white.copy(alpha = 0.6f).toArgb()
                        setShadowLayer(10f, 0f, 0f, gray.toArgb())
                    }
                )
            }

            drawCircle(
                color = white.copy(0.2f),
                radius = innerRadius.dp.toPx() + transparentWidth.dp.toPx() / 2f
            )

        }
    }
}

//Tutorial: https://www.youtube.com/watch?v=CGevyQxp8uw, but I did my on modifications to fit in All Screen Sizes