package br.com.vtvinicius.feature_experiments.side_modal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.vtvinicius.base_feature.extensions.AppScaffold
import br.com.vtvinicius.feature_experiments.FeatureExperimentsNavigation
import br.com.vtvinicius.uikit.base.greenExperimentsDark
import br.com.vtvinicius.uikit.base.greenExperimentsLight
import br.com.vtvinicius.uikit.ui.text.BodyMediumText
import br.com.vtvinicius.uikit.ui.topbar.AppTopBar
import br.com.vtvinicius.uikit.utils.extensions.HorizontalSpacer
import br.com.vtvinicius.uikit.utils.extensions.VerticalSpacer
import kotlinx.coroutines.launch

@Composable
fun SideModalScreen(navigation: FeatureExperimentsNavigation, navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    AppScaffold(
        topBar = {
            AppTopBar(
                title = "Side Modal",
                onBackPressed = { navigation.goToLobby(navController) },
                backgroundColor = greenExperimentsLight,
                textColor = greenExperimentsDark
            )
        },
        content = {

            ModalDrawer(
                drawerState = drawerState,
                drawerContent = {

                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        onClick = { scope.launch { drawerState.close() } },
                        content = { Text("Close Drawer") }
                    )

                    VerticalSpacer(height = 16)

                    LazyColumn {
                        items(10) {

                            VerticalSpacer(height = 12)

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {

                                HorizontalSpacer(width = 16)

                                Icon(
                                    Icons.Default.Call,
                                    contentDescription = "Localized description"
                                )

                                HorizontalSpacer(width = 24)

                                BodyMediumText("Item $it")

                            }
                        }
                    }
                },
                content = {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")

                        VerticalSpacer(20)

                        Button(onClick = { scope.launch { drawerState.open() } }) {
                            Text("Click to open")
                        }
                        VerticalSpacer(height = 32)
                    }
                }
            )
        })
}