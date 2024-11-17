package org.redev.rx.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.redev.rx.app.core.compositionProvider.CompositionProviderApp
import org.redev.rx.app.core.nav.NavGraphProvider
import org.redev.rx.app.router.NavGraphRouter

import taskapp.composeapp.generated.resources.Res
import taskapp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        CompositionProviderApp {
            NavGraphRouter()
        }
    }
}