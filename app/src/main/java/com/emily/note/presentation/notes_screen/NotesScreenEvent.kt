package com.emily.note.presentation.notes_screen

import com.emily.note.domain.model.Note

sealed interface NotesScreenEvent {
    data object OnAddNoteEvent: NotesScreenEvent
    data class OnDeleteNote(val note: Note): NotesScreenEvent
    data class OnClickNote(val id: Int): NotesScreenEvent
}