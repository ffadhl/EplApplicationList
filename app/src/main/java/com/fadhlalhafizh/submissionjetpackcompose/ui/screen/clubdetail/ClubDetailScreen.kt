package com.fadhlalhafizh.submissionjetpackcompose.ui.screen.clubdetail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Copyright
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.Stadium
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fadhlalhafizh.submissionjetpackcompose.R
import com.fadhlalhafizh.submissionjetpackcompose.di.Injection
import com.fadhlalhafizh.submissionjetpackcompose.ui.ViewModelFactory
import com.fadhlalhafizh.submissionjetpackcompose.ui.common.UIState

@Composable
fun ClubDetailScreen(
    clubId: Int,
    navigateBack: () -> Unit,
    viewModel: ClubDetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { stateUI ->
        when (stateUI) {
            is UIState.Loading -> {
                viewModel.getClubsById(clubId)
            }

            is UIState.Success -> {
                val data = stateUI.data
                ClubDetailInformation(
                    id = data.id,
                    image = data.image,
                    name = data.name,
                    nickname = data.nickname,
                    stadium = data.stadium,
                    location = data.location,
                    manager = data.manager,
                    description = data.description,
                    captain = data.captain,
                    isSave = data.isSave,
                    navigateBack = navigateBack
                ) { id, state ->
                    viewModel.updatePremiereLeagueClubs(id, state)
                }
            }

            is UIState.Error -> {}
        }
    }
}

@Composable
fun ClubDetailInformation(
    id: Int,
    @DrawableRes image: Int,
    name: String,
    nickname: String,
    stadium: String,
    location: String,
    manager: String,
    captain: String,
    description: String,
    isSave: Boolean,
    navigateBack: () -> Unit,
    onSaveButtonClicked: (id: Int, state: Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 64.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "ClubsName",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Campaign,
                        contentDescription = "nickname",
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(nickname)
                            }
                        },
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, end = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Stadium,
                        contentDescription = "stadium",
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(stadium)
                            }
                        },
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, end = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "location",
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(location)
                            }
                        },
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, end = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Man,
                        contentDescription = "manager",
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(manager)
                            }
                        },
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, end = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Copyright,
                        contentDescription = "captain",
                        tint = colorResource(id = R.color.black),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(captain)
                            }
                        },
                        fontSize = 14.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp, end = 8.dp)
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .background(colorResource(id = R.color.purple))
            )
            Text(
                text = stringResource(id = R.string.desc),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                color = Color.Black,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(color = colorResource(id = R.color.purple))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = navigateBack,
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = stringResource(id = R.string.goBack),
                        tint = colorResource(id = R.color.white),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = stringResource(id = R.string.clubDetail),
                    color = colorResource(id = R.color.white),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
                IconButton(
                    onClick = {
                        onSaveButtonClicked(id, isSave)
                    },
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = if (!isSave) Icons.Default.BookmarkBorder else Icons.Default.BookmarkAdded,
                        contentDescription = if (!isSave) stringResource(R.string.saved) else stringResource(
                            R.string.deleteSave
                        ),
                        tint = if (!isSave) Color.White else Color.White
                    )
                }
            }
        }
    }
}