package br.com.vtvinicius.uikit.ui.neumorphism

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicCard1(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    contentPadding: PaddingValues = PaddingValues(20.dp),
    onPressed: () -> Unit = {},
    onReleased: () -> Unit = {},
    content: @Composable() () -> Unit,
) {

    val backgroundColor = remember { mutableStateOf(Color.LightGray) }
    val borderColor = remember { mutableStateOf(Color.Gray) }

    val backgroundModifier = Modifier
        .shadow((-2).dp, shape, false, Color.White, Color.White)
        .height(150.dp)
        .width(300.dp)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    if (isPressed) {
        onPressed()
        backgroundColor.value = Color.White
        borderColor.value = Color.LightGray
    } else {
        onReleased()
        backgroundColor.value = Color.White
        borderColor.value = Color.White
    }

    Card(
        modifier = modifier.then(backgroundModifier),
        shape = shape,
        border = BorderStroke(5.dp, color = borderColor.value),
        elevation = 30.dp,
        backgroundColor = Color.White,
        content = {
            content()
        })
}

@Preview
@Composable
fun NeumorphicCardSamplePreview() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NeumorphicCard1(onClick = {}, content = {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text (text = "Neomorfismo")
            }
        })
    }

}