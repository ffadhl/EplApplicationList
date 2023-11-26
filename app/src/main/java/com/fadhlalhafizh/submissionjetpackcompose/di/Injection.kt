package com.fadhlalhafizh.submissionjetpackcompose.di

import com.fadhlalhafizh.submissionjetpackcompose.data.ClubsRepository

object Injection {
    fun provideRepository(): ClubsRepository {
        return ClubsRepository.getInstance()
    }
}