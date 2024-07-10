package com.emily.note.presentation.notes_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.emily.note.presentation.component.NoteComponent

@Composable
fun NotesScreen (
    viewModel: NotesViewModel = hiltViewModel(),
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        viewModel.navigate.collect { screen ->
            navController.navigate(screen.route)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { padding ->
        Text(
            text = "Your notes",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(
                items = viewModel.notes.value,
                key = { it.id }
            ) { note ->
                NoteComponent (
                    note = note,
                    onDelete = {
                        viewModel.onEvent(
                            NotesScreenEvent.OnDeleteNote(
                                note = note
                            )
                        )
                    },
                    onClickNote = {
                        viewModel.onEvent(
                            NotesScreenEvent.OnClickNote(
                                note.id
                            )
                        )
                    }
                )
            }
        }
        IconButton(
            onClick = {
                viewModel.onEvent(
                    NotesScreenEvent.OnAddNoteEvent
                )
            }
        ) {
            Icon(imageVector = Icons.Default.Create, contentDescription = null)
        }
    }
}
