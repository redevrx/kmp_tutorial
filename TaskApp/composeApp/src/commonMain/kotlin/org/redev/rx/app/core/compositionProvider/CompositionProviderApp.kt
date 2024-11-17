package org.redev.rx.app.core.compositionProvider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import org.redev.rx.app.core.nav.NavGraphProvider


@Composable
fun CompositionProviderApp(content: @Composable () -> Unit) {

    CompositionLocalProvider(NavGraphProvider provides rememberNavController(), content)
}
