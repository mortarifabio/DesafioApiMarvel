package com.mortarifabio.desafiowebservices.comics

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.mortarifabio.desafiowebservices.model.ComicsResult

class ComicsDataSourceFactory(
    private val context: Context
) : DataSource.Factory<Int, ComicsResult>() {
    private val comicsLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ComicsResult>>()

    override fun create(): DataSource<Int, ComicsResult> {
        val comicsDataSource = ComicsPageKeyedDataSource(context)
        comicsLiveDataSource.postValue(comicsDataSource)
        return comicsDataSource
    }

    fun getLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, ComicsResult>> {
        return comicsLiveDataSource
    }
}