package com.msjtrs.ing_app.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msjtrs.ing_app.domain.CommentProperty
import com.msjtrs.ing_app.network.JsonplaceholderApi
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty
import androidx.lifecycle.viewModelScope
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

    private val _navigateToSelectedProperty = MutableLiveData<PostProperty>()
    val navigateToSelectedProperty: LiveData<PostProperty>
        get() = _navigateToSelectedProperty

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                _status.value = AppStatus.LOADING
                val listUsers = JsonplaceholderApi.retrofitService.getUsers().await()
                val listComments = JsonplaceholderApi.retrofitService.getComments().await()
                val listPosts = JsonplaceholderApi.retrofitService.getPosts().await()
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
            for(user in userProperties.value!!) {
                if(post.userId == user.id) {
                    post.posterName = user.username
                    post.posterEmail = user.email
                    post.posterAddress = user.address
                    post.posterPhone = user.phone
                    post.posterWebsite = user.website
                    post.posterCompany = user.company
                    break
                }
            }
        }
    }

    private fun attachCommentCountToPosts() {
        for(comment in _commentProperties.value!!) {
            _postProperties.value?.get(comment.postId.toInt()-1)?.commentCount =
                _postProperties.value?.get(comment.postId.toInt()-1)?.commentCount?.plus(1)!!
        }
    }

    fun displayPropertyDetails(postProperty: PostProperty){
        _navigateToSelectedProperty.value = postProperty
    }

    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }
}