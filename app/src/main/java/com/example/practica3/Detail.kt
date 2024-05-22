package com.example.practica3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.practica3.ui.theme.Practica3Theme

class Detail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val premiered = intent.getStringExtra("premiered")
        val language = intent.getStringExtra("language")
        val summary = intent.getStringExtra("summary")
        val genres = intent.getStringExtra("genres")
        val country = intent.getStringExtra("country")
        super.onCreate(savedInstanceState)
        setContent {
            Practica3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    carta(name!!, image!!, premiered!!, language!!, summary!!, genres!!, country!!)
                }
            }
        }
    }
}