package com.search.app.ui.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterDetailsScreenViewModel: ViewModel() {
    private val _viewState = MutableStateFlow(CharacterDetailsScreenViewState())
    val viewState = _viewState.asStateFlow()


}