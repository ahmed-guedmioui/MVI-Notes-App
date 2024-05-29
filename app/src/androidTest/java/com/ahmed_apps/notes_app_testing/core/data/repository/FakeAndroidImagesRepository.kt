package com.ahmed_apps.notes_app_testing.core.data.repository

import com.ahmed_apps.notes_app_testing.core.domain.model.Images
import com.ahmed_apps.notes_app_testing.core.domain.repository.ImagesRepository

/**
 * @author Ahmed Guedmioui
 */
class FakeAndroidImagesRepository : ImagesRepository {

    private var shouldReturnError = false
    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun searchImages(
        query: String
    ): Images? {
        return if (shouldReturnError) {
            null
        } else {
            Images(
                listOf("image1", "image2", "image3", "image4", "image5", "image6")
            )
        }
    }
}


















