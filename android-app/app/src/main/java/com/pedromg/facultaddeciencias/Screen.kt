package com.pedromg.facultaddeciencias

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.pedromg.facultaddeciencias.ui.views.CalendarView
import com.pedromg.facultaddeciencias.ui.views.CounselingView
import com.pedromg.facultaddeciencias.ui.views.JobOffersView
import com.pedromg.facultaddeciencias.ui.views.NewsArticleView
import com.pedromg.facultaddeciencias.ui.views.NewsArticleViewModel
import com.pedromg.facultaddeciencias.ui.views.NewsView
import com.pedromg.facultaddeciencias.ui.views.NewsViewModel
import com.pedromg.facultaddeciencias.ui.views.ProceduresView
import com.pedromg.facultaddeciencias.ui.views.ProfessionalPracticeView
import com.pedromg.facultaddeciencias.ui.views.SocialServiceView
import com.pedromg.facultaddeciencias.ui.views.StudentsProjectsView
import com.pedromg.facultaddeciencias.ui.views.TeachersProjectsView

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: ImageVector = Icons.Default.Home,
    val content: @Composable (navBackStackEntry: NavBackStackEntry, navController: NavHostController) -> Unit,
    val arguments: List<NamedNavArgument> = listOf(),
    val isPrimary: Boolean = true,
    val basePath: String = ""
) {

    fun withParams(vararg params: String): String {
        return params.joinToString(
            prefix = "$basePath/",
            separator = "/"
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    object NewsScreen : Screen(
        route = "news_screen",
        title = "Noticias",
        icon = Icons.Default.Notifications,
        content = { _: NavBackStackEntry, navController: NavHostController ->
            val newsViewModel: NewsViewModel = viewModel(factory = NewsViewModel.NewsViewModelFactory())
            NewsView(navController, newsViewModel)
        }
    )

    @RequiresApi(Build.VERSION_CODES.O)
    object NewsArticleScreen : Screen(
        route = "news_article_screen/{articleId}",
        basePath = "news_article_screen",
        isPrimary = false,
        arguments = listOf(
            navArgument("articleId") {
                type = NavType.StringType
            }
        ),
        content = { navBackStackEntry: NavBackStackEntry, _: NavHostController ->
            val newsArticleViewModel: NewsArticleViewModel = viewModel(factory = NewsArticleViewModel.NewsArticleViewModelFactory())
            NewsArticleView(navBackStackEntry, newsArticleViewModel)
        }
    )

    object CalendarScreen : Screen(
        route = "calendar_screen",
        title = "Calendario de Actividades",
        icon = Icons.Default.DateRange,
        content = { _: NavBackStackEntry, _: NavHostController ->
            CalendarView()
        }
    )

    object CounselingScreen : Screen(
        route = "counseling_screen",
        title = "Asesorías",
        icon = Icons.Default.Person,
        content = { _: NavBackStackEntry, _: NavHostController ->
            CounselingView()
        }
    )

    object ProceduresScreen : Screen(
        route = "procedures_screen",
        title = "Trámites escolares",
        icon = Icons.Default.Info,
        content = { _: NavBackStackEntry, _: NavHostController ->
            ProceduresView()
        }
    )

    object SocialServiceScreen : Screen(
        route = "social_service_screen",
        title = "Programas de Servicio Social",
        icon = Icons.Default.Favorite,
        content = { _: NavBackStackEntry, _: NavHostController ->
            SocialServiceView()
        }
    )

    object ProfessionalPracticeScreen : Screen(
        route = "professional_practice_screen",
        title = "Modalidades de Aprendizaje y Prácticas Profesionales",
        icon = Icons.Default.AccountBox,
        content = { _: NavBackStackEntry, _: NavHostController ->
            ProfessionalPracticeView()
        }
    )

    object JobOffersScreen : Screen(
        route = "job_offers_screen",
        title = "Ofertas Laborales",
        icon = Icons.Default.Star,
        content = { _: NavBackStackEntry, _: NavHostController ->
            JobOffersView()
        }
    )

    object TeachersProjectsScreen : Screen(
        route = "teachers_projects_screen",
        title = "Proyectos de Investigación",
        icon = Icons.Default.Build,
        content = { _: NavBackStackEntry, _: NavHostController ->
            TeachersProjectsView()
        }
    )

    object StudentsProjectsScreen : Screen(
        route = "students_projects_screen",
        title = "Proyectos Cimarrones",
        icon = Icons.Default.Face,
        content = { _: NavBackStackEntry, _: NavHostController ->
            StudentsProjectsView()
        }
    )

}
