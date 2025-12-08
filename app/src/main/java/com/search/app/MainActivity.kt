package com.search.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.search.app.ui.theme.SearchAppTheme
import com.search.app.ui.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchAppTheme {
                Scaffold { padding ->
                    MainScreen(
                        modifier = Modifier
                            .padding(padding)
                    )
                }
            }
        }
    }
}