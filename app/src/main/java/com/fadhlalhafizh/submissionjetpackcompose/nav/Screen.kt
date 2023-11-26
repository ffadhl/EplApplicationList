package com.fadhlalhafizh.submissionjetpackcompose.nav

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Save : Screen("save")
    object AboutMe : Screen("aboutMe")
    object ClubDetail : Screen("home/{clubId}") {
        fun createRoute(clubId: Int) = "home/$clubId"
    }
}