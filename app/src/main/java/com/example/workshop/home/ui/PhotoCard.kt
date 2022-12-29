package com.example.workshop.home.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.unsplashed.client.model.Photo
import com.unsplashed.client.model.User

@Composable
fun UserInformation(user: User, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .testTag("userInfo:image")
                .size(48.dp)
                .clip(CircleShape)
                .border(2.5.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape),
            model = user.profileImage.medium,
            contentDescription = user.username
        )

        Column() {
            Text(text = user.name, style = MaterialTheme.typography.titleMedium)
            if (!user.location.isNullOrBlank()) {
                Text(
                    text = user.location!!,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoCard(photo: Photo, onClick: () -> Unit) {
    Card(onClick = onClick) {

        UserInformation(
            user = photo.user,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )

        AsyncImage(
            modifier = Modifier
                .testTag("image")
                .fillMaxWidth()
                .aspectRatio(photo.width.toFloat() / photo.height.toFloat()),
            model = photo.links.download,
            contentDescription = photo.altDescription
        )


        if (!photo.description.isNullOrBlank()) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = photo.description!!,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = if (photo.likedByUser) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text("${photo.likes}")
            }
        }
    }
}