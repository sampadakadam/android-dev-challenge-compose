package com.example.androiddevchallenge.network

import com.example.androiddevchallenge.model.Item
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    @GET("/v0/beststories.json")
    suspend fun bestStoryIds(): List<Long>

    @GET("v0/item/{id}.json")
    suspend fun getItem(@Path("id") id: Long): Item
}