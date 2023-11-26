package com.fadhlalhafizh.submissionjetpackcompose.ui.assetitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Stadium
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.fadhlalhafizh.submissionjetpackcompose.R

@Composable
fun ClubsItem(
    id: Int,
    image: Int,
    name: String,
    location: String,
    stadium: String,
    isSave: Boolean,
    onSaveIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(2.dp)
            .testTag("Card Item"),
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Transparent)
                        .clip(CircleShape)
                        .border(3.dp, colorResource(id = R.color.purple), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = "image_club",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location",
                            tint = Color.Black,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append(location)
                                }
                            },
                            style = MaterialTheme.typography.caption,
                            color = Color.Black
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Stadium,
                            contentDescription = "stadium",
                            tint = Color.Black,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                                    append(stadium)
                                }
                            },
                            style = MaterialTheme.typography.caption,
                            color = Color.Black,
                        )
                    }
                }
            }
            Icon(
                imageVector = if (isSave) Icons.Default.BookmarkAdded else Icons.Default.BookmarkBorder,
                contentDescription = null,
                tint = if (!isSave) colorResource(id = R.color.purple) else colorResource(id = R.color.purple),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .clickable {
                        onSaveIconClicked(id, !isSave)
                    }
                    .testTag("Save Icon")
            )
        }
    }
}