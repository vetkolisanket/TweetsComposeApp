package com.example.tweetscomposeapp.data

import android.content.Context
import com.example.tweetscomposeapp.models.TweetListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.IOException

object LocalDataSource {

    private var data: List<TweetListItem>? = null

    fun getDataFromAssets(context: Context): List<TweetListItem>? {
        if (data == null) {
            val jsonString: String
            try {
                jsonString =
                    context.assets.open("tweets.json").bufferedReader().use { it.readText() }
                data =
                    Gson().fromJson(jsonString, object : TypeToken<List<TweetListItem>>() {}.type)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return data
    }


}