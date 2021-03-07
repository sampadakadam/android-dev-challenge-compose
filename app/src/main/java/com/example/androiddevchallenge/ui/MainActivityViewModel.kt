package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.model.Item
import com.example.androiddevchallenge.repository.HackernewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val repository: HackernewsRepository) :
    ViewModel() {

    private val _uiState = MutableLiveData<List<Item>>()
    val uiState: LiveData<List<Item>> = _uiState

    //var item = MutableLiveData<Item>()

    fun start() {

        viewModelScope.launch {
//            repository.story(26337046).collect {
//                item.value = it
//            }


            repository.bestStories().collect { bests ->
                _uiState.value = bests
            }
        }
    }


    sealed class BestUiState {
        data class Success(val best: List<Int>) : BestUiState()
        data class Error(val exception: Throwable) : BestUiState()
    }
}