package com.pedromg.facultaddeciencias.ui.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import dev.jeziellago.compose.markdowntext.MarkdownText

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleView(mavBackStackEntry: NavBackStackEntry, viewModel: NewsArticleViewModel) {
    val articleId = mavBackStackEntry.arguments?.getString("articleId")
    val newsArticle = viewModel.newsArticle.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = newsArticle.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = newsArticle.publishedAt.toString(),
        fontSize =12.sp,




            

            modifier = Modifier.padding(bottom = 4.dp)
        )
        MarkdownText(markdown = newsArticle.content)
    }

    DisposableEffect(Unit) {
        viewModel.fetchArticle(articleId ?: "")
        onDispose {  }
    }

}
