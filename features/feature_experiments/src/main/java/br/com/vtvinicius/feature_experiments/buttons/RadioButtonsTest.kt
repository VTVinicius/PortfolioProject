package br.com.vtvinicius.feature_experiments.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import br.com.vtvinicius.uikit.base.*
import br.com.vtvinicius.uikit.utils.extensions.VerticalSpacer

//https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary


@Composable
fun RadioButtonsTest() {

    val radioOptions = listOf("Option 1", "Option 2", "Option 3")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior

    Column(Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {

            VerticalSpacer(height = 64)

            var state by remember { mutableStateOf(true) }

            Column(Modifier.selectableGroup()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state,
                        onClick = { state = true },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Red,
                            unselectedColor = redClonesDark
                        )

                    )
                    Text(text = "Option 1.1")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = !state,
                        onClick = { state = false },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Red,
                            unselectedColor = redClonesDark

                        )

                    )
                    Text(text = "Option 1.2")
                }
            }


        }
    }


}