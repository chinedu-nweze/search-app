package com.search.app.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.search.app.model.DataResult
import com.search.app.repository.ISearchRickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class MainScreenViewModel(
    private val repository: ISearchRickAndMortyRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainScreenViewState())
    val viewState = _viewState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        _searchQuery
            .debounce(500L)
            .distinctUntilChanged()
            .onEach { query ->
                if (query.isNotBlank()) {
                    performSearch(query)
                } else {
                    // Optionally, clear results if the search bar is empty
                    _viewState.update { it.copy(result = emptyList()) }
                }
            }
            .launchIn(viewModelScope) // Launch the flow collection
    }
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    private fun performSearch(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSearchResult(searchQuery)) {
                is DataResult.Success -> {
                    _viewState.update { state ->
                        state.copy(
                            result = result.data?.results ?: emptyList(),
                            isLoading = false,
                            errorMessage = null,
                        )
                    }
                }

                is DataResult.Error -> {
                    _viewState.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMessage = result.message,
                        )
                    }
                }

                is DataResult.Loading -> {
                    _viewState.update { state ->
                        state.copy(
                            isLoading = true,
                            errorMessage = null,
                        )
                    }
                }
            }
        }
    }
//    /**
//     * Fetch the characters from the API and update the state with the result
//     */
//    fun getCharacter(searchQuery: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            when (val result = repository.getSearchResult(searchQuery)) {
//                is DataResult.Success -> {
//                    _viewState.update { state ->
//                        state.copy(
//                            result = result.data?.results ?: emptyList(),
//                            isLoading = false,
//                            errorMessage = null,
//                        )
//                    }
//                }
//
//                is DataResult.Error -> {
//                    _viewState.update { state ->
//                        state.copy(
//                            isLoading = false,
//                            errorMessage = result.message,
//                        )
//                    }
//                }
//
//                is DataResult.Loading -> {
//                    _viewState.update { state ->
//                        state.copy(
//                            isLoading = true,
//                            errorMessage = null,
//                        )
//                    }
//                }
//            }
//        }
//    }
}