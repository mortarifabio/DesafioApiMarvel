package com.mortarifabio.desafiowebservices.comics

import android.content.Context
import android.util.Log
import com.mortarifabio.desafiowebservices.R
import com.mortarifabio.desafiowebservices.api.ApiService
import com.mortarifabio.desafiowebservices.api.ResponseApi
import com.mortarifabio.desafiowebservices.utils.Constants.Api.API_LIMIT
import com.mortarifabio.desafiowebservices.utils.Constants.Api.CHARACTER_ID

class ComicsRepository(
    private val context: Context
) {
    suspend fun getComicsByCharacter(page: Int): ResponseApi {
        val offset = API_LIMIT * (page - 1)
        return try {
            val response = ApiService.marvelApi.comicsByCharacter(CHARACTER_ID, API_LIMIT, offset)
            if (response.isSuccessful) {
                ResponseApi.Success(response.body())
            } else {
                ResponseApi.Error(context.getString(R.string.error_loading_data))
            }
        } catch (ex: Exception) {
            Log.i("API", ex.toString())
            ResponseApi.Error(context.getString(R.string.error_api_call))
        }
    }
}