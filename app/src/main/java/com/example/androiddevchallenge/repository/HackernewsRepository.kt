package com.example.androiddevchallenge.repository

import android.util.Log
import com.example.androiddevchallenge.model.Item
import com.example.androiddevchallenge.network.HackerNewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HackernewsRepository @Inject constructor(private val service: HackerNewsService) {

    fun bestStories(): Flow<List<Item>> = flow<List<Item>> {
        bestStoryIds()
            .collect { ids ->
                combine(ids.map { id -> story(id) })
                { items: Array<Item> ->
                    items.toList()
                }.collect {
                    emit(it)
                }
            }
    }.flowOn(Dispatchers.IO)

    fun bestStoryIds(): Flow<List<Long>> = flow {
        Log.i("tag", "called best story ids")
        val ids = service.bestStoryIds()
        emit(ids)
    }

    fun story(id: Long): Flow<Item> = flow {
        Log.i("tag", " called get item for $id")
        val item = service.getItem(id)
        emit(item)
    }

}