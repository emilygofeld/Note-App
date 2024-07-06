package com.emily.note.presentation.notes_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.emily.note.presentation.component.NoteComponent

@Preview
@Composable
fun NotesScreen (
    viewModel: NotesViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        LazyColumn {
//            items(viewModel.notes.value) { note ->
//                NoteComponent (
//                    note = note,
//                    onDelete = {
//                        viewModel.onEvent(
//                            NotesScreenEvent.OnDeleteNote(
//                                note = note
//                            )
//                        )
//                    },
//                    onClickNote = {
//                        viewModel.onEvent(
//                            NotesScreenEvent.OnClickNote(
//                                note.id
//                            )
//                        )
//                    }
//                )
//            }
//        }
    }
}




