package com.pedromg.facultaddeciencias.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate

data class NewsArticle(
    val id: String,
    val title: String,
    val content: String,
    val publishedAt: LocalDate
)

@RequiresApi(Build.VERSION_CODES.O)
fun createEmptyArticle(): NewsArticle {
    return NewsArticle("", "", "", LocalDate.now())
}
