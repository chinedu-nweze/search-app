package com.search.app.model

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Sealed Class that represents the navigation routes to Screens
 */
sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object CharacterScreen : Route(route = "character_screen")
    object CharacterDetailsScreen : Route(
        route = "character_details_screen/{name}/{imageUrl}/{species}/{status}/{origin}/{created}/{type}",
        arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
            },
            navArgument("imageUrl") {
                type = NavType.StringType
            },
            navArgument("species") {
                type = NavType.StringType
            },
            navArgument("status") {
                type = NavType.StringType
            },
            navArgument("origin") {
                type = NavType.StringType
            },
            navArgument("created") {
                type = NavType.StringType
            },
            navArgument("type") {
                type = NavType.StringType
            }
        )
    ) {
        fun createRoute(
            name: String,
            imageUrl: String,
            species: String,
            status: String,
            origin: String,
            created: String,
            type: String
        ): String {
            val encodeImageUrl = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
            return "character_details_screen/$name/$encodeImageUrl/$species/$status/$origin/$created/$type"
        }
    }
}