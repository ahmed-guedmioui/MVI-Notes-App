package com.ahmed_apps.notes_app_testing.core.data.repository

import com.ahmed_apps.notes_app_testing.core.data.mapper.toImages
import com.ahmed_apps.notes_app_testing.core.data.remote.api.ImagesApi
import com.ahmed_apps.notes_app_testing.core.domain.model.Images
import com.ahmed_apps.notes_app_testing.core.domain.repository.ImagesRepository
import javax.inject.Inject

/**
 * @author Ahmed Guedmioui
 */
class ImagesRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi
) : ImagesRepository {

    override suspend fun searchImages(
        query: String
    ): Images? {
        return imagesApi.searchImages(query)?.toImages()
    }

}


















