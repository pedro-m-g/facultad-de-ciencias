package com.pedromg.facultaddeciencias.ui.views

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pedromg.facultaddeciencias.dto.mapNewsArticle
import com.pedromg.facultaddeciencias.models.NewsArticle
import com.pedromg.facultaddeciencias.models.createEmptyArticle
import com.pedromg.facultaddeciencias.services.NewsClient
import com.pedromg.facultaddeciencias.services.RestTemplate
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class NewsArticleViewModel: ViewModel() {

    private val newsClient: NewsClient = RestTemplate.newsClient
    @RequiresApi(Build.VERSION_CODES.O)
    val newsArticle: MutableState<NewsArticle> = mutableStateOf(createEmptyArticle())

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchArticle(id: String) {
        viewModelScope.launch {
            try {
                val response = newsClient.getNewsArticle(id)
                newsArticle.value = mapNewsArticle(response)
            } catch (e: Exception) {
                Log.e("NewsArticleViewModel", e.toString())
            }
        }
    }

    class NewsArticleViewModelFactory : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsArticleViewModel::class.java)) {
                return NewsArticleViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}