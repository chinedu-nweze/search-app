package com.search.app.model

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val results: List<CharacterDetail>
)
