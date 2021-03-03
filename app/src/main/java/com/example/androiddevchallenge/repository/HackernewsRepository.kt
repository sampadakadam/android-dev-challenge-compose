package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.network.HackerNewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HackernewsRepository @Inject constructor(private val service: HackerNewsService) {

    fun bestStories(): Flow<List<Long>> = flow {
        val list = service.bestStories()
        emit(list)
    }.flowOn(Dispatchers.IO) // Use the IO thread for this Flow


}