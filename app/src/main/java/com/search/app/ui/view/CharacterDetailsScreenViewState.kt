package com.search.app.ui.view

import com.search.app.model.CharacterDetail

data class CharacterDetailsScreenViewState(
    //val character: CharacterDetail? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
