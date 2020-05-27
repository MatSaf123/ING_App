package com.msjtrs.ing_app.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.msjtrs.ing_app.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface APIService {
    @GET("/posts")
    fun getPosts(@Query("_start") a : String, @Query("_end") b : String):
            Deferred<List<PostProperty>>

    // alt: id_gte, id_lte

    @GET("/users")
    fun getUsers():
            Deferred<List<UserProperty>>

    @GET("/comments")
    fun getComments(@Query("postId_gte") a : String, @Query("postId_lte") b : String):
            Deferred<List<CommentProperty>>


    @GET("/photos")
    fun getPhotos():
        Deferred<List<PhotoProperty>>

    @GET("/albums")
    fun getAlbums():
        Deferred<List<AlbumProperty>>

}

object JsonplaceholderApi {
    val retrofitService : APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}
