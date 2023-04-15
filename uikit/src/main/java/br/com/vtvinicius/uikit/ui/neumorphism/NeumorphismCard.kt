package br.com.vtvinicius.uikit.ui.neumorphism

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicButton1(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    backgroundColor: Color = Color.LightGray,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable() () -> Unit,
) {
    val elevation = 4.dp
    val shadowColorLight = Color.White
    val shadowColorDark = Color.Gray

    val backgroundModifier = Modifier
        .shadow(elevation, shape, true, shadowColorLight)
        .shadow(-elevation, shape, true, shadowColorDark)
        .background(backgroundColor, shape)

    androidx.compose.material.Button(
        onClick = onClick,
        modifier = modifier.then(backgroundModifier),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onSurface
        ),
        contentPadding = contentPadding,
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
    ) {
        content()
    }
}

@Composable
fun NeumorphicButtonSample() {
    androidx.compose.foundation.layout.Box(
        contentAlignment = Alignment.Center
    ) {
        NeumorphicButton(
            onClick = { /* Do something on click */ },
            contentPadding = PaddingValues(16.dp),
            backgroundColor = Color(0xFFF0F0F0)
        ) {
            Text("Neumorphic Button", textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun NeumorphicButtonSamplePreview() {
    NeumorphicButtonSample()
}

@Composable
fun NeumorphicButton2(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    backgroundColor: Color = Color.LightGray,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable() () -> Unit,
) {
    val elevation = 4.dp
    val shadowColorLight = Color.White
    val shadowColorDark = Color.Gray

    val backgroundModifier = Modifier
        .shadow(elevation, shape, true, shadowColorLight)
        .shadow(-elevation, shape, false, shadowColorDark)
        .background(backgroundColor, shape)

    androidx.compose.material.Button(
        onClick = onClick,
        modifier = modifier.then(backgroundModifier),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onSurface
        ),
        contentPadding = contentPadding,
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
    ) {
        content()
    }
}


@Composable
fun NeumorphicButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    backgroundColor: Color = Color.LightGray,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    content: @Composable() () -> Unit,
) {
    val elevation = 1.dp
    val shadowColorLight = Color.Red
    val shadowColorDark = Color.Red

    val backgroundModifier = Modifier
        .shadow(elevation, shape, true, shadowColorLight)
        .shadow(-elevation, shape, true, shadowColorDark)
        .background(backgroundColor, shape)

    Button(
        onClick = onClick,
        modifier = backgroundModifier.then(modifier),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onSurface
        ),
        contentPadding = contentPadding,
        border = BorderStroke(0.dp, Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp, 1.dp, 0.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(1.dp)
                .background(shadowColorLight, shape)
        )
        Box(
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp, start = 1.dp, end = 1.dp)
                .background(backgroundColor, shape)
        ) {
            content()
        }
    }
}

//
//@Composable
//fun NeumorphicButtonVini(
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier,
//    shape: Shape = RoundedCornerShape(12.dp),
//    backgroundColor: Color = Color.LightGray,
//    contentPadding: PaddingValues = PaddingValues(8.dp),
//    content: @Composable() () -> Unit,
//) {
//    val elevation = 8.dp
//
//    Button(
//        onClick = onClick,
//        shape = shape,
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color.Transparent,
//            contentColor = MaterialTheme.colors.onSurface
//        ),
//        contentPadding = contentPadding,
//        border = BorderStroke(0.dp, Color.Transparent),
//        elevation = ButtonDefaults.elevation(0.dp, 1.dp, 0.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(1.dp)
//                .background(shadowColorLight, shape)
//        )
//        Box(
//            modifier = Modifier
//                .padding(top = 2.dp, bottom = 2.dp, start = 1.dp, end = 1.dp)
//                .background(backgroundColor, shape)
//        ) {
//            content()
//        }
//    }
//}

@Composable
fun NeumorphicButtonSample2() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        NeumorphicButton2(
            onClick = { /* Do something on click */ },
            contentPadding = PaddingValues(16.dp),
            backgroundColor = Color(0xFFF0F0F0)
        ) {
            Text("Neumorphic Button", textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun NeumorphicButtonSample3() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        NeumorphicButton2(
            onClick = { /* Do something on click */ },
            contentPadding = PaddingValues(16.dp),
            backgroundColor = Color(0xFFF0F0F0)
        ) {
            Text("Neumorphic Button 2", textAlign = TextAlign.Center)
        }
    }
}