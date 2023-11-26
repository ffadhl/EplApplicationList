package com.fadhlalhafizh.submissionjetpackcompose.ui.screen.save

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fadhlalhafizh.submissionjetpackcompose.R
import com.fadhlalhafizh.submissionjetpackcompose.di.Injection
import com.fadhlalhafizh.submissionjetpackcompose.model.PremierLeagueClubs
import com.fadhlalhafizh.submissionjetpackcompose.ui.ViewModelFactory
import com.fadhlalhafizh.submissionjetpackcompose.ui.assetitem.EmptyListItem
import com.fadhlalhafizh.submissionjetpackcompose.ui.common.UIState
import com.fadhlalhafizh.submissionjetpackcompose.ui.screen.homescreen.ListPremierLeagueClubs

@Composable
fun SaveScreen(
    navigateToClubDetailScreen: (Int) -> Unit,
    viewModel: SaveViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> {
                viewModel.getSavedClubs()
            }

            is UIState.Success -> {
                SavedClubsInformation(
                    listClubs = uiState.data,
                    navigateToClubDetailScreen = navigateToClubDetailScreen,
                    onSaveIconClicked = { id, newState ->
                        viewModel.updatePremiereLeagueClubs(id, newState)
                    }
                )
            }

            is UIState.Error -> {}
        }
    }
}

@Composable
fun SavedClubsInformation(
    listClubs: List<PremierLeagueClubs>,
    navigateToClubDetailScreen: (Int) -> Unit,
    onSaveIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(color = colorResource(id = R.color.purple)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.savedList),
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            if (listClubs.isNotEmpty()) {
                ListPremierLeagueClubs(
                    listPremierLeagueClubs = listClubs,
                    onSaveIconClicked = onSaveIconClicked,
                    navigateToClubDetailScreen = navigateToClubDetailScreen
                )
            } else {
                EmptyListItem(
                    alertMessage = stringResource(id = R.string.blankSave)
                )
            }
        }
    }
}

