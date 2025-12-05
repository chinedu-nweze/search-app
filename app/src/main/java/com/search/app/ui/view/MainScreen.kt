package com.search.app.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.search.app.R
import com.search.app.model.Route
import org.koin.compose.viewmodel.koinViewModel
import timber.log.Timber

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val navController = rememberNavController()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }
//    LaunchedEffect(searchQuery) {
//        if (searchQuery.isNotEmpty()) {
//            viewModel.getCharacter(searchQuery)
//        }
//    }

    LaunchedEffect(Unit) {
        viewModel.getCharacter("rick")
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = Color.Transparent,
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChanged = {
                    searchQuery = it
                },
                modifier = modifier,
            )
        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.CharacterScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.CharacterScreen.route) {
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                ) {
                    LazyColumn {
                        items(count = viewState.result.size) { index ->
                            val result = viewState.result[index]
                            Timber.i("name: ${result.name}")
                            Timber.i("imageUrl: ${result.image}")
                            Timber.i("species: ${result.species}")
                            Timber.i("status: ${result.status}")
                            Timber.i("type: ${result.type}")
                            Timber.i("origin: ${result.origin.name}")
                            Timber.i("created: ${result.created}")



                            ListItem(
                                headlineContent = {
                                    Text(
                                        text = result.name,
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                },
                                colors = ListItemDefaults
                                    .colors(containerColor = Color.Transparent),
                                modifier = Modifier
                                    .clickable {
                                        navController
                                            .navigate(
                                                Route.CharacterDetailsScreen.createRoute(
                                                    name = result.name,
                                                    imageUrl = result.image,
                                                    species = result.species,
                                                    status = result.status,
                                                    origin = result.origin.name,
                                                    created = result.created,
                                                    type = result.type
                                                )
                                            )
                                    }
                            )
                        }
                    }
                }
            }

            composable(
                route = Route.CharacterDetailsScreen.route,
                arguments = Route.CharacterDetailsScreen.arguments
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
                val species = backStackEntry.arguments?.getString("species") ?: ""
                val status = backStackEntry.arguments?.getString("status") ?: ""
                val type = backStackEntry.arguments?.getString("type") ?: ""
                val origin = backStackEntry.arguments?.getString("origin") ?: ""
                val created = backStackEntry.arguments?.getString("created") ?: ""

                CharacterDetailsScreen(
                    name = name,
                    imageUrl = imageUrl,
                    species = species,
                    status = status,
                    type = type,
                    created = created,
                    origin = origin,
                )
            }
        }

    }
}


@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets(0, 160, 0, 0),
) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(windowInsets.asPaddingValues())
            .semantics { isTraversalGroup = true }
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            label = {
                Text(
                    text = stringResource(id = R.string.search_bar_hint),
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_bar_description)
                )
            },
            singleLine = true,
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 4.dp),
        )
    }
}

