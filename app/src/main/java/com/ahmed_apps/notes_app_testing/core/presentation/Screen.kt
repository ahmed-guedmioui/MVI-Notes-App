package com.ahmed_apps.notes_app_testing.core.presentation

/**
 * @author Ahmed Guedmioui
 */
sealed interface Screen {
    @kotlinx.serialization.Serializable
    data object NoteList : Screen

    @kotlinx.serialization.Serializable
    data object AddNote : Screen
}