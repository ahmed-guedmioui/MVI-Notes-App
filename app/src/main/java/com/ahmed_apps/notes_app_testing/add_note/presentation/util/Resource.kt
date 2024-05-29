package com.ahmed_apps.notes_app_testing.add_note.presentation.util

/**
 * @author Ahmed Guedmioui
 */
sealed class Resource<T>(
    val data: T? = null,
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}