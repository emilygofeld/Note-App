package com.emily.note.presentation.add_edit_note

sealed interface AddEditNoteEvent {
    data class OnChangeTitle(val title: String): AddEditNoteEvent
    data class OnChangeContent(val content: String): AddEditNoteEvent
    data object OnSaveNote: AddEditNoteEvent
}
