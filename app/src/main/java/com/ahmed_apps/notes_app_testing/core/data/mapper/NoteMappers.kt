package com.ahmed_apps.notes_app_testing.core.data.mapper

import com.ahmed_apps.notes_app_testing.core.data.local.NoteEntity
import com.ahmed_apps.notes_app_testing.core.domain.model.NoteItem

/**
 * @author Ahmed Guedmioui
 */

fun NoteItem.toNoteEntityForInsert(
): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded
    )
}

fun NoteItem.toNoteEntityForDelete(
): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id
    )
}

fun NoteEntity.toNoteItem(): NoteItem {
    return NoteItem(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id ?: 0
    )
}














