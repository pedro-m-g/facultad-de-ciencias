package com.pedromg.facultaddeciencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.pedromg.facultaddeciencias.ui.theme.FacultadDeCienciasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacultadDeCienciasTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                )
                Navigation()
            }
        }
    }
}