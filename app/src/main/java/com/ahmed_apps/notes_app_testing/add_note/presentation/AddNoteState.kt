package com.ahmed_apps.notes_app_testing.add_note.presentation

/**
 * @author Ahmed Guedmioui
 */
data class AddNoteState(
    val imageUrl: String = "",
    val title: String = "",
    val description: String = "",

    val isImagesDialogShowing: Boolean = false,

    val imageList: List<String> = emptyList(),
    val searchImagesQuery: String = "",
    val isLoadingImages: Boolean = false,
)