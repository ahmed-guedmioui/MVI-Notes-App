package com.ahmed_apps.notes_app_testing.note_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed_apps.notes_app_testing.core.domain.model.NoteItem
import com.ahmed_apps.notes_app_testing.note_list.domain.use_case.DeleteNote
import com.ahmed_apps.notes_app_testing.note_list.domain.use_case.GetAllNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getAllNotes: GetAllNotes,
    private val deleteNote: DeleteNote
) : ViewModel() {

    private val _noteListState = MutableStateFlow<List<NoteItem>>(emptyList())
    val noteListState = _noteListState.asStateFlow()

    private val _orderByTitleState = MutableStateFlow(false)
    val orderByTitleState = _orderByTitleState.asStateFlow()

    fun loadNotes() {
        viewModelScope.launch {
            _noteListState.update {
                getAllNotes.invoke(orderByTitleState.value)
            }
        }
    }

    fun deleteNote(noteItem: NoteItem) {
        viewModelScope.launch {
            deleteNote.invoke(noteItem)
            loadNotes()
        }
    }

    fun changeOrder() {
        viewModelScope.launch {
            _orderByTitleState.update { !it }
            loadNotes()
        }
    }

}























