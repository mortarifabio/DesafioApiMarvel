package com.mortarifabio.desafiowebservices.comics

import android.content.Context
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.mortarifabio.desafiowebservices.api.ResponseApi
import com.mortarifabio.desafiowebservices.model.Comics
import com.mortarifabio.desafiowebservices.model.ComicsResult
import com.mortarifabio.desafiowebservices.utils.Constants.Api.API_FIRST_PAGE
import com.mortarifabio.desafiowebservices.utils.formatDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ComicsPageKeyedDataSource(
    private val context: Context
) : PageKeyedDataSource<Int, ComicsResult>() {

    private val repository: ComicsRepository by lazy {
        ComicsRepository(context)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ComicsResult>
    ) {
        loadInitialData(callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ComicsResult>) {
        loadData(params.key, params.key - 1, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ComicsResult>) {
        loadData(params.key, params.key + 1, callback)
    }

    private fun processData(comics: Comics): Comics {
        comics.data.results.forEach {
            if (it.images.isNotEmpty()) {
                val imagePath = it.images[0].path.replace("http", "https")
                it.image = "$imagePath/portrait_fantastic.${it.images[0].extension}"
                it.zoomImage = "$imagePath.${it.images[0].extension}"
                val bannerPath = it.images.last().path.replace("http", "https")
                it.banner = "$bannerPath/landscape_incredible.${it.images.last().extension}"
            }
            it.issueNumber = "#${it.issueNumber.replace(".0", "")}"
            if (it.description == null) {
                it.description = "No description available."
            }
            if (it.dates.isNotEmpty()) {
                val onSaleDate = it.dates.filter { date ->
                    date.type == "onsaleDate"
                }
                if (onSaleDate.isNotEmpty()) {
                    val formattedDate = onSaleDate[0].date.formatDate()
                    it.published = "${formattedDate.take(1).toUpperCase(Locale.ROOT)}${formattedDate.subSequence(1, formattedDate.length)}"
                }
            }
            if (it.prices.isNotEmpty()) {
                val printPrice = it.prices.filter { date ->
                    date.type == "printPrice"
                }
                if (printPrice.isNotEmpty()) {
                    it.price = "$ ${printPrice[0].price}"
                }
            }
        }
        return comics
    }

    private fun loadInitialData(callback: LoadInitialCallback<Int, ComicsResult>) {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = repository.getComicsByCharacter(API_FIRST_PAGE)) {
                is ResponseApi.Success -> {
                    val comics = response.data as Comics
                    processData(comics)
                    callback.onResult(comics.data.results, null, API_FIRST_PAGE + 1)
                }
                is ResponseApi.Error -> {
                    Log.e("API Error", response.message)
                    callback.onResult(mutableListOf(), null, API_FIRST_PAGE + 1)
                }
            }
        }
    }

    private fun loadData(page: Int, nextPage: Int, callback: LoadCallback<Int, ComicsResult>) {
        CoroutineScope(Dispatchers.IO).launch {
            when (val response = repository.getComicsByCharacter(page)) {
                is ResponseApi.Success -> {
                    val comics = response.data as Comics
                    processData(comics)
                    callback.onResult(comics.data.results, nextPage)
                }
                is ResponseApi.Error -> {
                    Log.e("API Error", response.message)
                    callback.onResult(mutableListOf(), nextPage)
                }
            }
        }
    }

}