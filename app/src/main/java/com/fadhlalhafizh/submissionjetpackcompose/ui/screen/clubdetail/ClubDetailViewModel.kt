package com.fadhlalhafizh.submissionjetpackcompose.ui.screen.clubdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlalhafizh.submissionjetpackcompose.data.ClubsRepository
import com.fadhlalhafizh.submissionjetpackcompose.model.PremierLeagueClubs
import com.fadhlalhafizh.submissionjetpackcompose.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClubDetailViewModel(private val clubsRepository: ClubsRepository) : ViewModel() {
    private val state: MutableStateFlow<UIState<PremierLeagueClubs>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<PremierLeagueClubs>>
        get() = state

    fun updatePremiereLeagueClubs(id: Int, newState: Boolean) = viewModelScope.launch {
        clubsRepository.updatePremiereLeagueClubs(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) getClubsById(id)
            }
    }

    fun getClubsById(id: Int) = viewModelScope.launch {
        state.value = UIState.Loading
        state.value = UIState.Success(clubsRepository.getClubsById(id))
    }
}