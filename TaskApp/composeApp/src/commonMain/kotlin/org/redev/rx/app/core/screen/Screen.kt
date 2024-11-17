package org.redev.rx.app.core.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.redev.rx.app.core.nav.NavGraphProvider


interface Screen {
    @Composable
    fun Content(nav:NavHostController = NavGraphProvider.current)


    val getContent
        @Composable
        get() = Content()
}
