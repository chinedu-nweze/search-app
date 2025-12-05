package com.search.app.ui.view

import com.search.app.model.CharacterDetail

data class MainScreenViewState(
    val result: List<CharacterDetail> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
