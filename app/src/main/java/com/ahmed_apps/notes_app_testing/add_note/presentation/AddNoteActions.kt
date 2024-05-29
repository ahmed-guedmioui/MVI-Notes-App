package com.ahmed_apps.notes_app_testing.add_note.presentation

/**
 * @author Ahmed Guedmioui
 */
sealed interface AddNoteActions {

    data class UpdateTitle(val newTitle: String) : AddNoteActions
    data class UpdateDescription(val newDescription: String) : AddNoteActions

    data class UpdateSearchImageQuery(val newQuery: String) : AddNoteActions
    data class PickImage(val imageUrl: String) : AddNoteActions

    data object UpdateImagesDialogVisibility : AddNoteActions
    data object SaveNote : AddNoteActions
}
