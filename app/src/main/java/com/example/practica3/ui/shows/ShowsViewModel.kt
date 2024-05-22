package com.example.practica3.ui.shows

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica3.data.DataProvider
import com.example.practica3.data.Show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowsViewModel : ViewModel() {
    private val dataProvider = DataProvider()
    val state: MutableState<List<Show>> = mutableStateOf(emptyList())

    fun loadData(context: Context) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataProvider.loadData(context, "shows.json")
                }
                state.value = dataProvider.Shows
            } catch (e: Exception) {
                // Manejar el error y/o hacer logging
                Log.e("ShowsViewModel", "Error loading data", e)
                state.value = emptyList() // o algún estado de error
            }
        }
    }
}