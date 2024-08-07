package com.emily.note.presentation.add_edit_note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
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
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(AddEditNoteState())
    val state = _state

    private val _navigate = Channel<Screen>()
    val navigate = _navigate.receiveAsFlow()

    private val id = savedStateHandle.get<Int>("id")

    init {
        viewModelScope.launch {
            if (id != null && id != Constants.NEW_NOTE) {
                repository.getNote(id)?.let { note ->
                    _state.value = state.value.copy(
                        title = note.title,
                        content = note.content
                    )
                }
            }
        }
    }

    fun onEvent (event: AddEditNoteEvent) {
        when(event) {
            is AddEditNoteEvent.OnChangeContent ->
                _state.value = state.value.copy(content = event.content)

            is AddEditNoteEvent.OnChangeTitle ->
                _state.value = state.value.copy(title = event.title)

            is AddEditNoteEvent.OnSaveNote -> {
                viewModelScope.launch {
                    repository.upsertNote(
                        Note(
                            title = _state.value.title,
                            content = _state.value.content,
                            timestamp = LocalDateTime.now(),
                            id = if (id == Constants.NEW_NOTE) null else id
                        )
                    )
                    _navigate.send(Screen.Notes)
                }
            }
        }
    }
}