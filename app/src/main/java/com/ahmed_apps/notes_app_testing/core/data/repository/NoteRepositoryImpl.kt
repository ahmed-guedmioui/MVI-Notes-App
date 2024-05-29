package com.ahmed_apps.notes_app_testing.core.data.repository

import com.ahmed_apps.notes_app_testing.core.data.local.NoteDb
import com.ahmed_apps.notes_app_testing.core.data.mapper.toNoteEntityForDelete
import com.ahmed_apps.notes_app_testing.core.data.mapper.toNoteEntityForInsert
import com.ahmed_apps.notes_app_testing.core.data.mapper.toNoteItem
import com.ahmed_apps.notes_app_testing.core.domain.model.NoteItem
import com.ahmed_apps.notes_app_testing.core.domain.repository.NoteRepository

/**
 * @author Ahmed Guedmioui
 */
class NoteRepositoryImpl(
    noteDb: NoteDb
) : NoteRepository {

    private val noteDao = noteDb.noteDao

    override suspend fun upsertNote(noteItem: NoteItem) {
        noteDao.upsertNoteEntity(noteItem.toNoteEntityForInsert())
    }

    override suspend fun deleteNote(noteItem: NoteItem) {
        noteDao.deleteNoteEntity(noteItem.toNoteEntityForDelete())
    }

    override suspend fun getAllNotes(): List<NoteItem> {
        return noteDao.getAllNoteEntities().map { it.toNoteItem() }
    }
}












