package com.msjtrs.ing_app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msjtrs.ing_app.network.JsonplaceholderApi
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _postProperties = MutableLiveData<List<PostProperty>>()
    val postProperties: LiveData<List<PostProperty>>
        get() = _postProperties

    private val _userProperties = MutableLiveData<List<UserProperty>>()
    val userProperties: LiveData<List<UserProperty>>
        get() = _userProperties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUserProperties()
        getPostProperties()
    }

    private fun getPostProperties() {
        coroutineScope.launch {
            val getPropertiesDeferred = JsonplaceholderApi.retrofitService.getPosts()
            try {
                val listResult = getPropertiesDeferred.await()
                _postProperties.value = listResult
                setPosterUsername()
            }
            catch(e: Exception) {
                _postProperties.value = ArrayList()
            }
        }
    }

    private fun getUserProperties() {
        coroutineScope.launch {
            val getPropertiesDeferred = JsonplaceholderApi.retrofitService.getUsers()
            try {
                val listResult = getPropertiesDeferred.await()
                _userProperties.value = listResult
            }
            catch(e: Exception) {
                _userProperties.value = ArrayList()
            }
        }
    }


    private fun setPosterUsername() {
        for(post in _postProperties.value!!) {
            for(user in userProperties.value!!) {
                if(post.userId == user.id) {
                    post.posterName = user.username
                    break
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
