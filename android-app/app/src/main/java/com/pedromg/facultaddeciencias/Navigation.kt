package com.pedromg.facultaddeciencias

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedRoute by rememberSaveable {
        mutableStateOf(Screen.NewsScreen.route)
    }
    val screens = listOf(
        Screen.NewsScreen,
        Screen.NewsArticleScreen,

        Screen.CalendarScreen,
        Screen.CounselingScreen,
        Screen.ProceduresScreen,
        Screen.SocialServiceScreen,
        Screen.ProfessionalPracticeScreen,
        Screen.JobOffersScreen,
        Screen.TeachersProjectsScreen,
        Screen.StudentsProjectsScreen
    )
    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                screens = screens,
                selectedRoute = selectedRoute
            ) { route ->
                selectedRoute = route
                navController.navigate(selectedRoute)
                scope.launch {
                    drawerState.close()
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = stringResource(R.string.menu_content_description)
                            )
                        }
                    },
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                    actions = {
                        Image(
                            painter = painterResource(id = R.drawable.facultad_de_ciencias_logo),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(36.dp)
                                .padding(end = 8.dp)
                        )
                    }
                )
            }
        ) { innerPadding ->
            Surface(
                color = Color.LightGray,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.NewsScreen.route
                ) {
                    screens.forEach { screen ->
                        composable(
                            route = screen.route,
                            arguments = screen.arguments,
                            content = {
                                screen.content(it, navController)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(
    screens: List<Screen>,
    selectedRoute: String,
    onRouteChange: (selectedRoute: String) -> Unit
) {
    ModalDrawerSheet {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF2E7D32))
                .padding(16.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.uabc_logo),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 28.sp,
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )

        screens
            .filter { it.isPrimary }
            .forEach {
                NavigationEntry(
                    screen = it,
                    selectedRoute = selectedRoute,
                    onRouteChange = onRouteChange
                )
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationEntry(
    screen: Screen,
    selectedRoute: String,
    onRouteChange: (route: String) -> Unit
) {
    NavigationDrawerItem(
        label = {
            Text(
                text = screen.title
            )
        },
        selected = selectedRoute == screen.route,
        onClick = {
            onRouteChange(screen.route)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title
            )
        },
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}
