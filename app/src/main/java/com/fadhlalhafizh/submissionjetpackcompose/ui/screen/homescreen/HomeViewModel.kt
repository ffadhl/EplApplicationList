package com.fadhlalhafizh.submissionjetpackcompose.ui.screen.homescreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlalhafizh.submissionjetpackcompose.data.ClubsRepository
import com.fadhlalhafizh.submissionjetpackcompose.model.PremierLeagueClubs
import com.fadhlalhafizh.submissionjetpackcompose.ui.common.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val clubsRepository: ClubsRepository) : ViewModel() {
    private val state: MutableStateFlow<UIState<List<PremierLeagueClubs>>> =
        MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<PremierLeagueClubs>>>
        get() = state

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun updatePremiereLeagueClubs(id: Int, newState: Boolean) = viewModelScope.launch {
        clubsRepository.updatePremiereLeagueClubs(id, newState)
            .collect { isUpdated ->
                if (isUpdated) searchPremiereLeagueClubs(_query.value)
            }
    }

    fun searchPremiereLeagueClubs(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        clubsRepository.searchPremiereLeagueClubs(_query.value)
            .catch {
                state.value = UIState.Error(it.message.toString())
            }
            .collect {
                state.value = UIState.Success(it)
            }
    }
}