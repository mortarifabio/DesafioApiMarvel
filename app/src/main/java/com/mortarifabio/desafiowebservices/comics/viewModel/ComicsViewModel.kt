package com.mortarifabio.desafiowebservices.comics.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.mortarifabio.desafiowebservices.comics.ComicsDataSourceFactory
import com.mortarifabio.desafiowebservices.model.ComicsResult
import com.mortarifabio.desafiowebservices.utils.Constants.Api.API_LIMIT

class ComicsViewModel(application: Application) : AndroidViewModel(application) {
    var comicsPagedList: LiveData<PagedList<ComicsResult>>? = null
    private var comicsLiveDataSource: LiveData<PageKeyedDataSource<Int, ComicsResult>>? = null

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(API_LIMIT).build()

        val comicsDataSourceFactory = ComicsDataSourceFactory(application)
        comicsLiveDataSource = comicsDataSourceFactory.getLiveDataSource()
        comicsPagedList = LivePagedListBuilder(comicsDataSourceFactory, pagedListConfig).build()
    }
}