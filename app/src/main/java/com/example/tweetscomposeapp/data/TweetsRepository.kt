package com.example.tweetscomposeapp.data

import android.content.Context
import com.example.tweetscomposeapp.api.TweetsAPI
import com.example.tweetscomposeapp.models.TweetListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TweetsRepository @Inject constructor(
    private val api: TweetsAPI,
    private val context: Context
) {

    private val _categoriesFlow = MutableStateFlow<List<String>>(emptyList())
    val categoriesFlow = _categoriesFlow.asStateFlow()

    private val _tweetsFlow = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweetsFlow = _tweetsFlow.asStateFlow()

    suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            val categories =
                LocalDataSource.getDataFromAssets(context)?.map { it.category }?.distinct()
            categories?.let { _categoriesFlow.value = it }
        }
    }

    suspend fun getTweets(category: String) {
        withContext(Dispatchers.IO) {
            val tweets =
                LocalDataSource.getDataFromAssets(context)?.filter { it.category == category }
            tweets?.let { _tweetsFlow.value = it }
        }
    }

}