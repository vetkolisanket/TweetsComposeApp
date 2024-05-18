package com.example.tweetscomposeapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetscomposeapp.data.TweetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val repository: TweetsRepository,
    private val savedStateHandle: SavedStateHandle
    ) :
    ViewModel() {

    val tweets = repository.tweetsFlow

    init {
        fetchTweets()
    }

    private fun fetchTweets() {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "Entertainment"
            repository.getTweets(category)
        }
    }

}