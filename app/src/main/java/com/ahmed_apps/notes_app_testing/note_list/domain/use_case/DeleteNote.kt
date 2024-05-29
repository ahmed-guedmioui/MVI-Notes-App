package com.ahmed_apps.notes_app_testing.note_list.domain.use_case

import com.ahmed_apps.notes_app_testing.core.domain.model.NoteItem
import com.ahmed_apps.notes_app_testing.core.domain.repository.NoteRepository

/**
 * @author Ahmed Guedmioui
 */
class DeleteNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: NoteItem) {
        noteRepository.deleteNote(note)
    }

}
















