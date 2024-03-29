package br.com.vtvinicius.feature_canvas.star

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.vtvinicius.uikit.R

@Composable
fun FiveStars(
    modifier: Modifier = Modifier,
    onStarSelected: (Stars) -> Unit,
    distanceBetweenStars: Dp = 35.dp,
    animStarTimeFill: Int = 300,
    animStarTimeEmpty: Int = 200,
    colorStarUnselected: Color = Color.LightGray,
    icon1PathString: String = stringResource(id = R.string.star_path),
    icon2PathString: String = stringResource(id = R.string.star_path),
    icon3PathString: String = stringResource(id = R.string.star_path),
    icon4PathString: String = stringResource(id = R.string.star_path),
    icon5PathString: String = stringResource(id = R.string.star_path),
    icon1Color: Color = Color(0XFFffd700),
    icon2Color: Color = Color(0XFFffd700),
    icon3Color: Color = Color(0XFFffd700),
    icon4Color: Color = Color(0XFFffd700),
    icon5Color: Color = Color(0XFFffd700),
    sizeStars: Dp = 24.dp,
    iconsAlignment: Alignment = Alignment.Center,

) {

    //Declara as Variaveis de Controle de Clicks
    var center by remember {
        mutableStateOf(Offset.Unspecified)
    }
    var currentClickOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var selectedStar by remember {
        mutableStateOf<Stars>(Stars.Star0)
    }

    //Declara as Variaveis de Conversão de String para Path
    val star1Path = remember {
        PathParser().parsePathString(icon1PathString).toPath()
    }
    val star2Path = remember {
        PathParser().parsePathString(icon2PathString).toPath()
    }
    val star3Path = remember {
        PathParser().parsePathString(icon3PathString).toPath()
    }
    val star4Path = remember {
        PathParser().parsePathString(icon4PathString).toPath()
    }
    val star5Path = remember {
        PathParser().parsePathString(icon5PathString).toPath()
    }

    //Declara as Variaveis controlam o tamanho da estrela
    val star1PathBounds = remember {
        star1Path.getBounds()
    }
    val star2PathBounds = remember {
        star2Path.getBounds()
    }
    val star3PathBounds = remember {
        star3Path.getBounds()
    }
    val star4PathBounds = remember {
        star4Path.getBounds()
    }
    val star5PathBounds = remember {
        star5Path.getBounds()
    }

    //Declara as variaveis de animação
    var star1TranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var star2TranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var star3TranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var star4TranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }
    var star5TranslationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    //Declara as variaveis que controlam qual estrela foi selecionada
    var targetValue1 by remember {
        mutableStateOf(0f)
    }
    var targetValue2 by remember {
        mutableStateOf(0f)
    }
    var targetValue3 by remember {
        mutableStateOf(0f)
    }
    var targetValue4 by remember {
        mutableStateOf(0f)
    }
    var targetValue5 by remember {
        mutableStateOf(0f)
    }

    //Faz a validação de quais estrela tem que preencher quando clicada. No caso se a Quarta estrela foi selecionada, preenche todas para tras.
    targetValue1 =
        if (selectedStar is Stars.Star1 || selectedStar is Stars.Star2 || selectedStar is Stars.Star3 || selectedStar is Stars.Star4 || selectedStar is Stars.Star5) 80f else 0f
    targetValue2 =
        if (selectedStar is Stars.Star2 || selectedStar is Stars.Star3 || selectedStar is Stars.Star4 || selectedStar is Stars.Star5) 80f else 0f
    targetValue3 =
        if (selectedStar is Stars.Star5 || selectedStar is Stars.Star4 || selectedStar is Stars.Star3) 80f else 0f
    targetValue4 =
        if (selectedStar is Stars.Star4 || selectedStar is Stars.Star5) 80f else 0f
    targetValue5 =
        if (selectedStar is Stars.Star5) 80f else 0f


    //Controla as Animações de preenchimento e despreenchimento das estrelas.
    //A animação para preenchimento por algum motivo é mais rapida que a de despreenchimento, então foi necessário fazer uma correção para que as animações fiquem Parecidas.
    val star1SelectionRadius = animateFloatAsState(
        targetValue = targetValue1,
        animationSpec = if (targetValue1 == 80f) tween(durationMillis = animStarTimeFill * 1) else tween(
            durationMillis = (animStarTimeEmpty / 2) * 5
        )
    )
    val star2SelectionRadius = animateFloatAsState(
        targetValue = targetValue2,
        animationSpec = if (targetValue2 == 80f)
            tween(durationMillis = animStarTimeFill * 2)
        else tween(durationMillis = (animStarTimeEmpty / 2) * 4
        )
    )
    val star3SelectionRadius = animateFloatAsState(
        targetValue = targetValue3,
        animationSpec = if (targetValue3 == 80f) tween(durationMillis = animStarTimeFill * 3) else tween(
            durationMillis = (animStarTimeEmpty / 2) * 3
        )
    )
    val star4SelectionRadius = animateFloatAsState(
        targetValue = targetValue4,
        animationSpec = if (targetValue4 == 80f) tween(durationMillis = animStarTimeFill * 4) else tween(
            durationMillis = (animStarTimeEmpty) / 2 * 2
        )
    )
    val star5SelectionRadius = animateFloatAsState(
        targetValue = targetValue5,
        animationSpec = if (targetValue5 == 80f) tween(durationMillis = animStarTimeFill * 5) else tween(
            durationMillis = (animStarTimeEmpty / 2) * 1
        )
    )

    BoxWithConstraints(modifier = modifier.fillMaxWidth().height(sizeStars), contentAlignment = iconsAlignment) {

        Canvas(modifier = modifier
            .fillMaxWidth()
            .height(sizeStars)
            .align(iconsAlignment)
            .pointerInput(true) {

                // Controla o Click na tela

                detectTapGestures {
                    val transformedStar1Rect = Rect(
                        offset = star1TranslationOffset,
                        size = star1PathBounds.size * (size.height / star1PathBounds.height)
                    )
                    val transformedStar2Rect = Rect(
                        offset = star2TranslationOffset,
                        size = star2PathBounds.size * (size.height / star2PathBounds.height)
                    )
                    val transformedStar3Rect = Rect(
                        offset = star3TranslationOffset,
                        size = star3PathBounds.size *( size.height / star3PathBounds.height)
                    )
                    val transformedStar4Rect = Rect(
                        offset = star4TranslationOffset,
                        size = star4PathBounds.size * (size.height / star4PathBounds.height)

                    )
                    val transformedStar5Rect = Rect(
                        offset = star5TranslationOffset,
                        size = star5PathBounds.size * (size.height / star5PathBounds.height)
                    )

                    //Verifica se o click na tela foi feito dentro de alguma estrela.

                    if (transformedStar1Rect.contains(it)) {
                        selectedStar = Stars.Star1
                        onStarSelected(selectedStar)
                    } else if (transformedStar2Rect.contains(it)) {
                        selectedStar = Stars.Star2
                        onStarSelected(selectedStar)
                    } else if (transformedStar3Rect.contains(it)) {
                        selectedStar = Stars.Star3
                        onStarSelected(selectedStar)
                    } else if (transformedStar4Rect.contains(it)) {
                        selectedStar = Stars.Star4
                        onStarSelected(selectedStar)
                    } else if (transformedStar5Rect.contains(it)) {
                        selectedStar = Stars.Star5
                        onStarSelected(selectedStar)
                    }
                }
            }) {

            center = this.center


            //Controla a posição da tela onde a estrela vai ser desenhada. Tambem controla o Espaçamento entre elas.

            star1TranslationOffset = Offset(
                x = star1PathBounds.width * (size.height / star1PathBounds.height) / 2f,
                y = center.y - star1PathBounds.height * (size.height / star1PathBounds.height) / 2f
            )

            star2TranslationOffset = Offset(
                x = star1TranslationOffset.x + star1PathBounds.width + distanceBetweenStars.toPx(),
                y = center.y - star1PathBounds.height * (size.height / star2PathBounds.height) / 2f
            )

            star3TranslationOffset = Offset(
                x = star2TranslationOffset.x + star1PathBounds.width + distanceBetweenStars.toPx(),
                y = center.y - star1PathBounds.height * (size.height / star3PathBounds.height) / 2f
            )

            star4TranslationOffset = Offset(
                x = star3TranslationOffset.x + star1PathBounds.width + distanceBetweenStars.toPx(),
                y = center.y - star1PathBounds.height * (size.height / star4PathBounds.height) / 2f
            )

            star5TranslationOffset = Offset(
                x = star4TranslationOffset.x + star1PathBounds.width + distanceBetweenStars.toPx(),
                y = center.y - star1PathBounds.height * (size.height / star5PathBounds.height) / 2f
            )


            //Faz o controle, para quando haver clique, ele inicia o preenchimento a partir do Centro,
            // no despreenchimento ele vai até o Offset Zero, no caso fora da estrela

            val untransformedStar1ClickOffset = if (currentClickOffset == Offset.Zero) {
                star1PathBounds.center
            } else {
                (currentClickOffset - star1TranslationOffset) / (size.height / star1PathBounds.height)
            }

            val untransformedStar2ClickOffset = if (currentClickOffset == Offset.Zero) {
                star2PathBounds.center
            } else {
                (currentClickOffset - star2TranslationOffset) / (size.height / star2PathBounds.height)
            }

            val untransformedStar3ClickOffset = if (currentClickOffset == Offset.Zero) {
                star3PathBounds.center
            } else {
                (currentClickOffset - star3TranslationOffset) / (size.height / star3PathBounds.height)
            }

            val untransformedStar4ClickOffset = if (currentClickOffset == Offset.Zero) {
                star4PathBounds.center
            } else {
                (currentClickOffset - star4TranslationOffset) / (size.height / star4PathBounds.height)
            }

            val untransformedStar5ClickOffset = if (currentClickOffset == Offset.Zero) {
                star5PathBounds.center
            } else {
                (currentClickOffset - star5TranslationOffset) / (size.height / star5PathBounds.height)
            }


            //Essa é a função que desenha de fato as estrelas e o preenchimento delas
            //Inicio da estrela 1
            translate(
                left = star1TranslationOffset.x,
                top = star1TranslationOffset.y
            ) {
                scale(
                    scale = size.height / star1PathBounds.height,
                    pivot = star1PathBounds.topLeft
                ) {
                    drawPath(
                        path = star1Path,
                        color = colorStarUnselected,
                    )
                    clipPath(
                        path = star1Path
                    ) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    icon1Color,
                                    icon1Color
                                ),
                                center = untransformedStar1ClickOffset,
                                radius = star1SelectionRadius.value + 0.1f
                            ),
                            radius = star1SelectionRadius.value / 4,
                            center = untransformedStar1ClickOffset
                        )
                    }
                }
            }
            //Fim da estrela 1

            //Inicio da estrela 2
            translate(
                left = star2TranslationOffset.x,
                top = star2TranslationOffset.y
            ) {
                scale(
                    scale = size.height / star2PathBounds.height,
                    pivot = star2PathBounds.topLeft
                ) {
                    drawPath(
                        path = star2Path,
                        color = colorStarUnselected,
                    )
                    clipPath(
                        path = star2Path
                    ) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    icon2Color,
                                    icon2Color
                                ),
                                center = untransformedStar2ClickOffset,
                                radius = star2SelectionRadius.value + 0.1f
                            ),
                            radius = star2SelectionRadius.value,
                            center = untransformedStar2ClickOffset
                        )
                    }
                }
            }
            //Fim da estrela 2

            //Inicio da estrela 3
            translate(
                left = star3TranslationOffset.x,
                top = star3TranslationOffset.y
            ) {
                scale(
                    scale = size.height / star3PathBounds.height,
                    pivot = star3PathBounds.topLeft
                ) {
                    drawPath(
                        path = star3Path,
                        color = colorStarUnselected,
                    )
                    clipPath(
                        path = star3Path
                    ) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    icon3Color,
                                    icon3Color
                                ),
                                center = untransformedStar3ClickOffset,
                                radius = star3SelectionRadius.value + 0.1f
                            ),
                            radius = star3SelectionRadius.value,
                            center = untransformedStar3ClickOffset
                        )
                    }
                }
            }
            //Fim da estrela 3

            //Inicio da estrela 4
            translate(
                left = star4TranslationOffset.x,
                top = star4TranslationOffset.y
            ) {
                scale(
                    scale = size.height / star4PathBounds.height,
                    pivot = star4PathBounds.topLeft
                ) {
                    drawPath(
                        path = star4Path,
                        color = colorStarUnselected
                    )
                    clipPath(
                        path = star4Path
                    ) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    icon4Color,
                                    icon4Color
                                ),
                                center = untransformedStar4ClickOffset,
                                radius = star4SelectionRadius.value + 0.1f
                            ),
                            radius = star4SelectionRadius.value,
                            center = untransformedStar4ClickOffset
                        )
                    }
                }
            }
            //Fim da estrela 4

            //Inicio da estrela 5
            translate(
                left = star5TranslationOffset.x,
                top = star5TranslationOffset.y
            ) {
                scale(
                    scale = size.height / star5PathBounds.height,
                    pivot = star5PathBounds.topLeft
                ) {
                    drawPath(
                        path = star5Path,
                        color = colorStarUnselected
                    )
                    clipPath(
                        path = star5Path
                    ) {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    icon5Color,
                                    icon5Color
                                ),
                                center = untransformedStar5ClickOffset,
                                radius = star5SelectionRadius.value + 0.1f
                            ),
                            radius = star5SelectionRadius.value,
                            center = untransformedStar5ClickOffset
                        )
                    }
                }
            }
            //Fim da estrela 5
        }
    }
}