package com.msjtrs.ing_app.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.msjtrs.ing_app.network.JsonplaceholderApi
import com.msjtrs.ing_app.domain.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.Thread.sleep
import kotlin.collections.ArrayList

enum class AppStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<AppStatus>()
    val status: LiveData<AppStatus>
        get() = _status

    private val _postProperties = MutableLiveData<List<PostProperty>>()
    val postProperties: LiveData<List<PostProperty>>
        get() = _postProperties

    private val _postPropertiesPaged = MutableLiveData<List<PostProperty>>()
    val postPropertiesPaged: LiveData<List<PostProperty>>
        get() = _postPropertiesPaged

    private val _userProperties = MutableLiveData<List<UserProperty>>()
    val userProperties: LiveData<List<UserProperty>>
        get() = _userProperties

    private val _commentProperties = MutableLiveData<List<CommentProperty>>()
    val commentProperties: LiveData<List<CommentProperty>>
        get() = _commentProperties

    private val _commentsSorted = MutableLiveData<MutableList<MutableList<CommentProperty>>>()
    val commentsSorted: LiveData<MutableList<MutableList<CommentProperty>>>
        get() = _commentsSorted

    private val _photoProperties = MutableLiveData<List<PhotoProperty>>()
    val photoProperties: LiveData<List<PhotoProperty>>
        get() = _photoProperties

    private val _albumProperties = MutableLiveData<List<AlbumProperty>>()
    val albumProperties: LiveData<List<AlbumProperty>>
        get() = _albumProperties

    private val _navigateToUserProperty = MutableLiveData<UserProperty>()
    val navigateToUserProperty: LiveData<UserProperty>
        get() = _navigateToUserProperty

    private val _navigateToCommentProperty = MutableLiveData<PostProperty>()
    val navigateToCommentProperty: LiveData<PostProperty>
        get() = _navigateToCommentProperty

    private var postPagingLimit : Int = 10

    init {
        _postPropertiesPaged.value = arrayListOf()
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                _status.value = AppStatus.LOADING
                val listUsers = JsonplaceholderApi.retrofitService.getUsers().await()

                //response times are too long:
                //val listPosts = JsonplaceholderApi.retrofitService.getPosts(10).await()
                //val listComments = JsonplaceholderApi.retrofitService.getComments(10).await()    //will work only if posts and comments are in order by id

                val listPosts = JsonplaceholderApi.retrofitService.getPosts().await()
                val listComments = JsonplaceholderApi.retrofitService.getComments().await()
                val listPhotos = JsonplaceholderApi.retrofitService.getPhotos().await()
                val listAlbums = JsonplaceholderApi.retrofitService.getAlbums().await()

                _status.value = AppStatus.DONE

                _photoProperties.value = listPhotos
                _albumProperties.value = listAlbums
                _userProperties.value = listUsers
                _commentProperties.value = listComments
                _postProperties.value = listPosts

            }
            catch(e: Exception) {
                Log.d("Error_TryCatch",e.message.toString())
                _status.value = AppStatus.ERROR
                _photoProperties.value = ArrayList()
                _albumProperties.value = ArrayList()
                _userProperties.value = ArrayList()
                _commentProperties.value = ArrayList()
                _postProperties.value = ArrayList()
            }

            setPosterName()
            sortCommentsIntoLists()
            attachCommentsToPosts()
            attachAlbumsToUsers()
            loadMorePosts()
        }
    }

    internal fun loadMorePosts() : Boolean {
        return try{
            _postPropertiesPaged.value = _postProperties.value!!.subList(0,postPagingLimit)
            postPagingLimit +=10
            true
        } catch(e: Exception) {
            false
        }
    }

    private fun setPosterName() {
        for(post in _postProperties.value!!) {
            val user : UserProperty? = _userProperties.value!![post.userId.toInt()-1]
            if (user != null)
                post.posterName = user.username
        }
    }

    private fun sortCommentsIntoLists() {
        val aList : MutableList<MutableList<CommentProperty>> = arrayListOf()
        for(post in _postProperties.value!!) {
            aList.add(post.id.toInt()-1, arrayListOf())
        }
        for(comment in _commentProperties.value!!) {
            aList[comment.postId.toInt()-1].add(comment)
        }
        _commentsSorted.value = aList
    }

    private fun attachCommentsToPosts() {
        for(list in _commentsSorted.value!!) {
            _postProperties.value!![list[0].postId.toInt()-1].commentCount = list.toList().size
            _postProperties.value!![list[0].postId.toInt()-1].comments = list.toList()
        }
    }

    private fun attachAlbumsToUsers() {
        val aList : MutableList<MutableList<PhotoProperty>> = arrayListOf()

        for(album in _albumProperties.value!!)
            aList.add(arrayListOf())

        for(photo in _photoProperties.value!!)
            aList[photo.albumId.toInt()-1].add(photo)

        for(album in _albumProperties.value!!)
            album.photos = aList[album.id.toInt()-1]

        for(user in _userProperties.value!!) {
            var list : MutableList<AlbumProperty> = ArrayList()
            for(album in _albumProperties.value!!)
                if(user.id == album.userId)
                    list.add(album)

            user.albums = list
        }
    }

    fun displayUserProperties(postProperty: PostProperty){
        val userProperty : UserProperty = _userProperties.value!![postProperty.userId.toInt()-1]
        _navigateToUserProperty.value = userProperty
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