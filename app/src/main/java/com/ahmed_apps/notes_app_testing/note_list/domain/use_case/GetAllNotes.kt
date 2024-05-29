package com.ahmed_apps.notes_app_testing.note_list.domain.use_case

import com.ahmed_apps.notes_app_testing.core.domain.model.NoteItem
import com.ahmed_apps.notes_app_testing.core.domain.repository.NoteRepository

/**
 * @author Ahmed Guedmioui
 */
class GetAllNotes(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(
        isOrderByTitle: Boolean
    ): List<NoteItem> {
        return if (isOrderByTitle) {
            noteRepository.getAllNotes().sortedBy { it.title.lowercase() }
        } else {
            noteRepository.getAllNotes().sortedBy { it.dateAdded }
        }
    }

}



















