package com.msjtrs.ing_app.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msjtrs.ing_app.domain.CommentProperty
import com.msjtrs.ing_app.network.JsonplaceholderApi
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class AppStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<AppStatus>()
    val status: LiveData<AppStatus>
        get() = _status

    private val _postProperties = MutableLiveData<List<PostProperty>>()
    val postProperties: LiveData<List<PostProperty>>
        get() = _postProperties

    private val _userProperties = MutableLiveData<List<UserProperty>>()
    val userProperties: LiveData<List<UserProperty>>
        get() = _userProperties

    private val _commentProperties = MutableLiveData<List<CommentProperty>>()
    val commentProperties: LiveData<List<CommentProperty>>
        get() = _commentProperties


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getData()
    }

    private fun getData() {
        coroutineScope.launch {
            val users = JsonplaceholderApi.retrofitService.getUsers()
            val comments = JsonplaceholderApi.retrofitService.getComments()
            val posts = JsonplaceholderApi.retrofitService.getPosts()
            try {
                _status.value = AppStatus.LOADING
                val listUsers = users.await()
                val listComments = comments.await()
                val listPosts = posts.await()
                _status.value = AppStatus.DONE

                _userProperties.value = listUsers
                _commentProperties.value = listComments
                _postProperties.value = listPosts

                setPosterUsername()
                attachCommentCountToPosts()
            }
            catch(e: Exception) {
                _status.value = AppStatus.ERROR
                _userProperties.value = ArrayList()
                _commentProperties.value = ArrayList()
                _postProperties.value = ArrayList()
            }
        }
    }
    
    private fun setPosterUsername() {
        for(post in _postProperties.value!!) {
            post.posterName = _userProperties.value?.get(post.userId.toInt()-1)?.username.toString()
        }
    }

    private fun attachCommentCountToPosts() {
        for(comment in _commentProperties.value!!) {
            _postProperties.value?.get(comment.postId.toInt()-1)?.commentCount =
                _postProperties.value?.get(comment.postId.toInt()-1)?.commentCount?.plus(1)!!
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}