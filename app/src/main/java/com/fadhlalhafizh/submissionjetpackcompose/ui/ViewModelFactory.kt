package com.fadhlalhafizh.submissionjetpackcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fadhlalhafizh.submissionjetpackcompose.data.ClubsRepository
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.clubdetail.ClubDetailViewModel
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.homescreen.HomeViewModel
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.save.SaveViewModel

class ViewModelFactory(private val clubsRepository: ClubsRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(clubsRepository) as T
        } else if (modelClass.isAssignableFrom(ClubDetailViewModel::class.java)) {
            return ClubDetailViewModel(clubsRepository) as T
        } else if (modelClass.isAssignableFrom(SaveViewModel::class.java)) {
            return SaveViewModel(clubsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}