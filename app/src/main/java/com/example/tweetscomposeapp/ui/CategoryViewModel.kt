package com.example.tweetscomposeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetscomposeapp.data.TweetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: TweetsRepository) :
    ViewModel() {

    val categories = repository.categoriesFlow

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            repository.getCategories()
        }
    }

}