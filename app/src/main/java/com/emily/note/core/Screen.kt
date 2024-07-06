package com.emily.note.core

sealed class Screen(val route: String) {
    data object Notes: Screen("notes")
    data class AddEditNote(val id: Int): Screen("add_edit_note/$id")
}