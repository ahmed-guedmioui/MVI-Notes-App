package com.ahmed_apps.notes_app_testing.core.domain.model

/**
 * @author Ahmed Guedmioui
 */
data class NoteItem(
    var title: String,
    var description: String,
    var imageUrl: String,

    val dateAdded: Long,

    val id: Int = 0,
)
