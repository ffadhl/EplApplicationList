package com.fadhlalhafizh.submissionjetpackcompose.ui.screen.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlalhafizh.submissionjetpackcompose.data.ClubsRepository
import com.fadhlalhafizh.submissionjetpackcompose.model.PremierLeagueClubs
import com.fadhlalhafizh.submissionjetpackcompose.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SaveViewModel(private val clubsRepository: ClubsRepository) : ViewModel() {
    private val state: MutableStateFlow<UIState<List<PremierLeagueClubs>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<PremierLeagueClubs>>>
        get() = state

    fun updatePremiereLeagueClubs(id: Int, newState: Boolean) {
        clubsRepository.updatePremiereLeagueClubs(id, newState)
        getSavedClubs()
    }

    fun getSavedClubs() = viewModelScope.launch {
        clubsRepository.getSavedClubs()
            .catch { e ->
                state.value = UIState.Error(e.message.toString())
            }
            .collect {
                state.value = UIState.Success(it)
            }
    }
}