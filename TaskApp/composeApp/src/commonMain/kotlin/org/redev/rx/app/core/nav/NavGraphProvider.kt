package org.redev.rx.app.core.nav

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController


val NavGraphProvider = compositionLocalOf<NavHostController> {
    error("error: can not provider NavController")
}


fun NavController.pust(destination: Destination) {
    kotlin.runCatching {
        to(destination)
    }.onFailure {
        error("error navigate to screen: $it")
    }
}

fun NavController.pop() {
    navigateUp()
}