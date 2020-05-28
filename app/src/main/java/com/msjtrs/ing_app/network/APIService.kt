package com.msjtrs.ing_app.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.msjtrs.ing_app.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val okHttpClient : OkHttpClient = OkHttpClient()
    .newBuilder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface APIService {
    /*
    @GET("/posts")
    fun getPosts(@Query("id_lte") b : Int):
            Deferred<List<PostProperty>>            //response time on this API is too long for such way of pagination
    */
    // alt: id_gte, id_lte

    @GET("/posts")
    fun getPosts():
            Deferred<List<PostProperty>>

    @GET("/users")
    fun getUsers():
            Deferred<List<UserProperty>>

    /*
    @GET("/comments")
    fun getComments(@Query("postId_lte") b : Int):  //response time on this API is too long for such way of pagination
            Deferred<List<CommentProperty>>
    */

    @GET("/comments")
    fun getComments():
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
