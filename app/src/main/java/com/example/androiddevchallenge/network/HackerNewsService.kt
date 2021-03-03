package com.example.androiddevchallenge.network

import retrofit2.http.GET

interface HackerNewsService {
    @GET("/v0/beststories.json")
    suspend fun bestStories(): List<Long>
}