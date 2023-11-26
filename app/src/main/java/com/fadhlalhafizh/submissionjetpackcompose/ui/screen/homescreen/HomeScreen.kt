package com.fadhlalhafizh.submissionjetpackcompose.ui.screen.homescreen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fadhlalhafizh.submissionjetpackcompose.R
import com.fadhlalhafizh.submissionjetpackcompose.di.Injection
import com.fadhlalhafizh.submissionjetpackcompose.model.PremierLeagueClubs
import com.fadhlalhafizh.submissionjetpackcompose.ui.ViewModelFactory
import com.fadhlalhafizh.submissionjetpackcompose.ui.assetitem.ClubsItem
import com.fadhlalhafizh.submissionjetpackcompose.ui.assetitem.EmptyListItem
import com.fadhlalhafizh.submissionjetpackcompose.ui.assetitem.SearchItem
import com.fadhlalhafizh.submissionjetpackcompose.ui.common.UIState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> {
                viewModel.searchPremiereLeagueClubs(query)
            }

            is UIState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::searchPremiereLeagueClubs,
                    listPremierLeagueClubs = uiState.data,
                    onSaveIconClicked = { id, newState ->
                        viewModel.updatePremiereLeagueClubs(id, newState)
                    },
                    navigateToClubDetailScreen = navigateToDetail
                )
            }

            is UIState.Error -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListPremierLeagueClubs(
    listPremierLeagueClubs: List<PremierLeagueClubs>,
    onSaveIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    navigateToClubDetailScreen: (Int) -> Unit,
    contentPaddingTop: Dp = 0.dp,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = contentPaddingTop
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("lazyList")
    ) {
        items(listPremierLeagueClubs, key = { it.id }) { premierLeagueClubs ->
            ClubsItem(
                id = premierLeagueClubs.id,
                image = premierLeagueClubs.image,
                name = premierLeagueClubs.name,
                location = premierLeagueClubs.location,
                stadium = premierLeagueClubs.stadium,
                isSave = premierLeagueClubs.isSave,
                onSaveIconClicked = onSaveIconClicked,
                modifier = Modifier
                    .animateItemPlacement(tween(durationMillis = 500))
                    .clickable { navigateToClubDetailScreen(premierLeagueClubs.id) },
            )
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listPremierLeagueClubs: List<PremierLeagueClubs>,
    onSaveIconClicked: (id: Int, newState: Boolean) -> Unit,
    navigateToClubDetailScreen: (Int) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {
        Column {
            SearchItem(
                query = query,
                onQueryChange = onQueryChange
            )
            if (listPremierLeagueClubs.isNotEmpty()) {
                ListPremierLeagueClubs(
                    listPremierLeagueClubs = listPremierLeagueClubs,
                    onSaveIconClicked = onSaveIconClicked,
                    navigateToClubDetailScreen = navigateToClubDetailScreen
                )
            } else {
                EmptyListItem(
                    alertMessage = stringResource(R.string.blankData),
                    modifier = Modifier
                        .testTag("emptyList")
                )
            }
        }
    }
}