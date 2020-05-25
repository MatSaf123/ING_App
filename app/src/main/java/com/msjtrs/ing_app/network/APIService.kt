package com.msjtrs.ing_app.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.msjtrs.ing_app.domain.CommentProperty
import com.msjtrs.ing_app.domain.PostProperty
import com.msjtrs.ing_app.domain.UserProperty
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
    fun getPosts(@Query("id_gte") gte : String, @Query("id_lte") lte : String):
            Deferred<List<PostProperty>>

    @GET("/users")
    fun getUsers():
            Deferred<List<UserProperty>>

    @GET("/comments")
    fun getComments(@Query("postId_gte") gte : String, @Query("postId_lte") lte : String):
            Deferred<List<CommentProperty>>

}

object JsonplaceholderApi {
    val retrofitService : APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}

/*
    @GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
 */

/*

QUERY = http://jsonplaceholder.typicode.com/photos ?_start=0&_limit=5

 */