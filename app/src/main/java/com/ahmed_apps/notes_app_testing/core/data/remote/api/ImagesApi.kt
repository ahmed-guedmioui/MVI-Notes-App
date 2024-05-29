package com.ahmed_apps.notes_app_testing.core.data.remote.api

import com.ahmed_apps.notes_app_testing.core.data.remote.dto.ImageListDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ahmed Guedmioui
 */
interface ImagesApi {

    @GET("/api/")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("key") apiKey: String = API_KEY,
    ): ImageListDto?

    companion object {
        const val BASE_URL = "https://pixabay.com"
        const val API_KEY = "43875405-6c70a3c2a57ec40d3140e2032"
    }

}





















