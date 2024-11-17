package org.redev.rx.app.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.redev.rx.app.core.nav.NavGraphProvider
import org.redev.rx.app.presentation.screen.DateTimeDetailScreen
import org.redev.rx.app.presentation.screen.HomeScreen
import org.redev.rx.app.presentation.screen.TaskDetailScreen


@Composable
fun NavGraphRouter(
    controller: NavHostController = NavGraphProvider.current,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = controller,
        startDestination = HomeScreenRoute,
        modifier = modifier
    ) {
        composable<HomeScreenRoute> {
            HomeScreen().getContent
        }
        composable<DateTimeDetailScreenRoute> {
            DateTimeDetailScreen().getContent
        }
        composable<TaskDetailScreenRoute> {
            TaskDetailScreen().getContent
        }
    }
}
