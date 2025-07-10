package com.pedromg.facultaddeciencias.services

import com.pedromg.facultaddeciencias.dto.NewsArticleDto
import com.pedromg.facultaddeciencias.dto.NewsArticleListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsClient {

    @GET("news")
    suspend fun getNews(): NewsArticleListResponse

    @GET("news/{articleId}")
    suspend fun getNewsArticle(@Path("articleId") articleId: String): NewsArticleDto

}