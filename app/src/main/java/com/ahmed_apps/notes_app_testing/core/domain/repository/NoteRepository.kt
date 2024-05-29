package com.ahmed_apps.notes_app_testing.core.domain.repository

import com.ahmed_apps.notes_app_testing.core.domain.model.NoteItem

/**
 * @author Ahmed Guedmioui
 */
interface NoteRepository {

    suspend fun upsertNote(noteItem: NoteItem)

    suspend fun deleteNote(noteItem: NoteItem)

    suspend fun getAllNotes(): List<NoteItem>

}


















