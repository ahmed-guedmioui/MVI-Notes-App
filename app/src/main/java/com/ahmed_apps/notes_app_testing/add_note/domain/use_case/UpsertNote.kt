package com.ahmed_apps.notes_app_testing.add_note.domain.use_case

import android.provider.ContactsContract.CommonDataKinds.Note
import com.ahmed_apps.notes_app_testing.core.domain.model.NoteItem
import com.ahmed_apps.notes_app_testing.core.domain.repository.NoteRepository

/**
 * @author Ahmed Guedmioui
 */
class UpsertNote(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(
        title: String,
        description: String,
        imageUrl: String
    ): Boolean {

        if (title.isEmpty() || description.isEmpty()) {
            return false
        }

        val note = NoteItem(
            title = title,
            description = description,
            dateAdded = System.currentTimeMillis(),
            imageUrl = imageUrl
        )

        noteRepository.upsertNote(note)
        return true

    }

}




















