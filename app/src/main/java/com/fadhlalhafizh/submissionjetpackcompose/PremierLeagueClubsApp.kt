package com.fadhlalhafizh.submissionjetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fadhlalhafizh.submissionjetpackcompose.nav.NavItem
import com.fadhlalhafizh.submissionjetpackcompose.nav.Screen
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.about.AboutScreen
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.clubdetail.ClubDetailScreen
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.homescreen.HomeScreen
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.save.SaveScreen

@Composable
fun PremierLeagueClubsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.ClubDetail.route) {
                BottomNavigationBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen { clubId ->
                    navController.navigate(Screen.ClubDetail.createRoute(clubId))
                }
            }
            composable(Screen.Save.route) {
                SaveScreen(
                    navigateToClubDetailScreen = { clubId ->
                        navController.navigate(Screen.ClubDetail.createRoute(clubId))
                    }
                )
            }
            composable(Screen.AboutMe.route) {
                AboutScreen()
            }
            composable(
                route = Screen.ClubDetail.route,
                arguments = listOf(
                    navArgument("clubId") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("clubId") ?: -1
                ClubDetailScreen(
                    clubId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavHostController, modifier: Modifier = Modifier) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavItem(
                title = stringResource(R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavItem(
                title = stringResource(R.string.save),
                icon = Icons.Default.Bookmark,
                screen = Screen.Save
            ),
            NavItem(
                title = stringResource(R.string.aboutMe),
                icon = Icons.Default.AccountCircle,
                screen = Screen.AboutMe
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = if (currentRoute == item.screen.route) Color.White else colorResource(
                                id = R.color.purple_light
                            )
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
