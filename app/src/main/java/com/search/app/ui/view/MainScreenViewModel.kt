package com.search.app.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.search.app.model.DataResult
import com.search.app.repository.ISearchRickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: ISearchRickAndMortyRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainScreenViewState())
    val viewState = _viewState.asStateFlow()


    /**
     * Fetch the characters from the API and update the state with the result
     */
    fun getCharacter(searchQuery: String) {
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
}