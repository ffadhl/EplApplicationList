package com.fadhlalhafizh.submissionjetpackcompose.model

data class PremierLeagueClubs(
    val id: Int,
    val image: Int,
    val name: String,
    val nickname: String,
    val stadium: String,
    val location: String,
    val description: String,
    val manager: String,
    val captain: String,
    var isSave: Boolean = false
)