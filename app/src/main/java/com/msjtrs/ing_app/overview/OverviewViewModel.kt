package com.msjtrs.ing_app.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msjtrs.ing_app.network.JsonplaceholderApi
import com.msjtrs.ing_app.network.PostProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _postProperties = MutableLiveData<List<PostProperty>>()
    val postProperties: LiveData<List<PostProperty>>
        get() = _postProperties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPostProperties()
    }

    private fun getPostProperties() {
        coroutineScope.launch {
            val getPropertiesDeferred = JsonplaceholderApi.retrofitService.getPosts()
            try {
                val listResult = getPropertiesDeferred.await()
                _postProperties.value = listResult
            }
            catch(e: Exception) {
                _postProperties.value = ArrayList()
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
