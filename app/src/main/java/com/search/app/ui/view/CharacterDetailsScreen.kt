package com.search.app.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.search.app.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    type: String,
    species: String,
    status: String,
    origin: String,
    created: String,
    viewModel: CharacterDetailsScreenViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    CharacterDetailsScreenContent(
        modifier = modifier,
        name = name,
        imageUrl = imageUrl,
        species = species,
        status = status,
        origin = origin,
        type = type,
        created = created
    )
}


@Composable
fun CharacterDetailsScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String,
    species: String,
    status: String,
    origin: String,
    type: String,
    created: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge
        )

        AsyncImage(
            modifier = Modifier.size(300.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),

            contentDescription = stringResource(id = R.string.image_of_character, name)
        )

        CharacterProperty(
            label = stringResource(id = R.string.species),
            value = species
        )
        CharacterProperty(
            label = stringResource(id = R.string.status),
            value = status
        )
        CharacterProperty(
            label = stringResource(id = R.string.origin),
            value = origin
        )

        if (type.isNotEmpty()) {
            CharacterProperty(label = stringResource(id = R.string.type), value = type)
        }

        CharacterProperty(
            label = stringResource(id = R.string.created),
            value = created
        )
    }
}

@Composable
private fun CharacterProperty(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterPropertyPreview() {
    CharacterProperty(label = "Species", value = "Human")
}