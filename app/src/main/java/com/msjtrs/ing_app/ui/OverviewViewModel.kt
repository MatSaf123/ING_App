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
import kotlin.collections.ArrayList

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

    private val _commentsSorted = MutableLiveData<MutableList<MutableList<CommentProperty>>>()
    val commentsSorted: LiveData<MutableList<MutableList<CommentProperty>>>
        get() = _commentsSorted

    private val _navigateToUserProperty = MutableLiveData<PostProperty>()
    val navigateToUserProperty: LiveData<PostProperty>
        get() = _navigateToUserProperty

    private val _navigateToCommentProperty = MutableLiveData<PostProperty>()
    val navigateToCommentProperty: LiveData<PostProperty>
        get() = _navigateToCommentProperty

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

            }
            catch(e: Exception) {
                Log.d("Error_TryCatch",e.message.toString())
                _status.value = AppStatus.ERROR
                _userProperties.value = ArrayList()
                _commentProperties.value = ArrayList()
                _postProperties.value = ArrayList()
            }

            setPosterProperties()
            attachCommentsToPostsDEV()
            sortCommentsIntoLists()     //new
            attachCommentsToPosts()     //new
        }
    }

    //TODO: Refactor, redo to pass UserProperty object instead of strings
    private fun setPosterProperties() {
        for(post in _postProperties.value!!) {
            val user : UserProperty? = _userProperties.value?.get(post.userId.toInt()-1)
            if(user != null) {
                post.posterName = user.username
                post.posterEmail = user.email
                post.posterWebsite = user.website
                post.posterStreet = user.address.street
                post.posterCity = user.address.city
                post.posterZipcode = user.address.zipcode
                post.posterGeoLatitude = user.address.geo.lat
                post.posterGeoLongitude = user.address.geo.lng
            }
        }
    }

    private fun attachCommentsToPostsDEV() {
        for(comment in _commentProperties.value!!) {
            _postProperties.value!![comment.postId.toInt()-1].commentBody=comment.body
            _postProperties.value!![comment.postId.toInt()-1].commentName=comment.name
            _postProperties.value!![comment.postId.toInt()-1].commentEmail=comment.email
        }
    }
    // NEW:
    private fun sortCommentsIntoLists() {
        val aList : MutableList<MutableList<CommentProperty>> = arrayListOf()

        for(post in _postProperties.value!!) {
            aList.add(arrayListOf())
        }

        for(comment in _commentProperties.value!!) {
            aList[comment.postId.toInt()-1].add(comment)
        }

        _commentsSorted.value = aList
    }
    // NEW:
    private fun attachCommentsToPosts() {
        for(list in _commentsSorted.value!!) {
            _postProperties.value!![list[0].postId.toInt()-1].commentCount = list.toList().size
            _postProperties.value!![list[0].postId.toInt()-1].comments = list.toList()
        }
    }

    fun displayUserProperties(postProperty: PostProperty){
        _navigateToUserProperty.value = postProperty
    }

    fun displayUserPropertiesComplete(){
        _navigateToUserProperty.value = null
    }

    fun displayCommentProperties(postProperty: PostProperty){
        _navigateToCommentProperty.value = postProperty
    }

    fun displayCommentPropertiesComplete(){
        _navigateToCommentProperty.value = null
    }

}