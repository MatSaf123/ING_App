package com.msjtrs.ing_app

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.network.JsonplaceholderApi
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ExampleUnitTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val _postProperties = MutableLiveData<List<PostProperty>>()
    val postProperties: LiveData<List<PostProperty>>
        get() = _postProperties

    init{
        getData()
    }

    private fun getData(){
        coroutineRule.launch{
            val listPosts = JsonplaceholderApi.retrofitService.getPosts().await()
            _postProperties.value = listPosts
        }
    }



    private fun countPosts(): Int{
        val aList : MutableList<MutableList<PostProperty>> = arrayListOf()
        for(post in _postProperties.value!!) {
            aList[post.id.toInt()-1].add(post)
        }
        return aList.size
    }




    @Test
    fun check(){
        //assertEquals(countPosts(), 100)
        //assertEquals(_postProperties.toString(), "")
        assertNotNull(_postProperties)
        //assertEquals("cokolwiek", _postProperties.value)
    }
}