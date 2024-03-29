package br.com.vtvinicius.feature_canvas.animated_arrows

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPath() {
    val pathPortion = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = true) {
        pathPortion.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2500
            )
        )
    }

    Canvas(modifier = Modifier.fillMaxSize()) {

        val path = Path().apply {
            moveTo(300.dp.toPx(), 800.dp.toPx())
            quadraticBezierTo(200.dp.toPx(), 500.dp.toPx(), 190.dp.toPx(), 700.dp.toPx())
        }
        val outPath = Path()
        PathMeasure().apply {
            setPath(path, false)
            getSegment(0f, pathPortion.value * length, outPath, true)
        }


        drawPath(
            path = outPath,
            color = Color.Red,
            style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
        )
    }


}