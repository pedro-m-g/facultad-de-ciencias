package com.pedromg.facultaddeciencias.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.pedromg.facultaddeciencias.models.NewsArticle
import java.time.LocalDate

data class NewsArticleListResponse(
    val current_page: Int,
    val data: List<NewsArticleDto>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: String?,
    val path: String,
    val per_page: Int,
    val prev_page_url: String?,
    val to: Int,
    val total: Int
)

data class NewsArticleDto(
    val id: String,
    val title: String,
    val content: String,
    val published_at: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun mapNewsArticleList(response: NewsArticleListResponse): List<NewsArticle> {
    return response.data.map { mapNewsArticle(it) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun mapNewsArticle(response: NewsArticleDto): NewsArticle {
    return NewsArticle(
        response.id,
        response.title,
        response.content,
        LocalDate.parse(response.published_at)
    )
}
