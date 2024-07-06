package com.emily.note.presentation.notes_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emily.note.core.Constants
import com.emily.note.core.Screen
import com.emily.note.domain.model.Note
import com.emily.note.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {
    private var _notes = mutableStateOf(emptyList<Note>())
    val notes = _notes

    private val _navigate = Channel<Screen>()
    val navigate = _navigate.receiveAsFlow()

    init {
        viewModelScope.launch {
            repository.getNotes().collect {
                _notes.value = it
            }
        }
    }

    fun onEvent (event: NotesScreenEvent) {
        viewModelScope.launch {
            when (event) {
                NotesScreenEvent.OnAddNoteEvent ->
                    _navigate.send(Screen.AddEditNote(Constants.NEW_NOTE))
                is NotesScreenEvent.OnClickNote ->
                    _navigate.send(Screen.AddEditNote(event.id))
                is NotesScreenEvent.OnDeleteNote ->
                    repository.deleteNote(event.note)
            }
        }
    }
}