package com.example.practica3.data

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DataProvider {
    var Shows: List<Show> = emptyList()
        private set

    fun loadData(context: Context, fileName: String){
        val showsJsonString = context.assets.open(fileName).bufferedReader().use{
            it.readText()
        }
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(Array<Show>::class.java)
        //adapta la info
        Shows = adapter.fromJson(showsJsonString)?.toList() ?: emptyList()
    }
}