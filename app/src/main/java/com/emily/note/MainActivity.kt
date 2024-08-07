package com.emily.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emily.note.core.Screen
import com.emily.note.presentation.add_edit_note.AddEditNoteScreen
import com.emily.note.presentation.notes_screen.NotesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.Notes.route) {
                composable(Screen.Notes.route) {
                    NotesScreen(navController = navController)
                }
                composable(
                    route = Screen.AddEditNote.ROUTE,
                    arguments = listOf(navArgument("id") {type = NavType.IntType})
                ) {
                    AddEditNoteScreen(navController = navController)
                }
            }
        }
    }
}


