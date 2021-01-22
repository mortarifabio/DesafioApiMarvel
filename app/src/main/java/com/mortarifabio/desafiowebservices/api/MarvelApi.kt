package com.mortarifabio.desafiowebservices.api

import com.mortarifabio.desafiowebservices.model.Comics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters/{characterId}/comics")
    suspend fun comicsByCharacter(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<Comics>
}