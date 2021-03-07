package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.model.Item
import com.example.androiddevchallenge.network.HackerNewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HackernewsRepository @Inject constructor(private val service: HackerNewsService) {

    fun bestStories(): Flow<List<Item>> = flow<List<Item>> {
        bestStoryIds()
            .collect { ids ->
                combine(ids.map { id -> story(id) }
                ) { items: Array<Item> ->
                    items.toList()
                }
            }
    }.flowOn(Dispatchers.IO)

    fun bestStoryIds(): Flow<List<Long>> = flow {
        val ids = service.bestStoryIds()
        emit(ids)
    }.flowOn(Dispatchers.IO)

    fun story(id: Long): Flow<Item> = flow {
        val item = service.getItem(id)
        emit(item)
    }.flowOn(Dispatchers.IO)

}