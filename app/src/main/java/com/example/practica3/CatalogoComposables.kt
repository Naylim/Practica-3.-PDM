package com.example.practica3

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.practica3.data.Show
import com.example.practica3.ui.shows.ShowsViewModel

private val showsViewModel = ShowsViewModel()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun catalogo() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "SHOWS", fontSize = 25.sp)
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {values ->
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            showsViewModel.loadData(context)
        }

        val showsState by showsViewModel.state

        LazyVerticalGrid(
            contentPadding = PaddingValues(15.dp), //espacio de bordes exteriores
            columns = GridCells.Adaptive(150.dp), //columnas adaptativas
            modifier = Modifier.padding(values)
        ){

            items(showsState){ show ->
                MediaItem(show, Modifier.padding(8.dp))
                //los items seran cards, recorriendo la lista de plantas
            }
        }
    }
}


@Composable
fun MediaItem(item : Show, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .clickable {
                val intent = Intent(context, Detail::class.java)
                intent.putExtra("name", item.name)
                intent.putExtra("image", item.image?.original)
                intent.putExtra("premiered", item.premiered)
                intent.putExtra("language", item.language)
                intent.putExtra("summary", item.summary)

                val genresString = item.genres?.joinToString(separator = ", ")
                val country = "${item.network.country?.name}, ${item.network.country?.code}"

                intent.putExtra("genres", genresString)
                intent.putExtra("country", country)


                context.startActivity(intent)
            },
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp)
            ) {
                AsyncImage(
                    model = item.image?.medium,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column {
                    Text(item.name.toString(), fontSize = 18.sp)
                    val genresString = item.genres?.joinToString(separator = ", ")
                    Text(
                        text = genresString.toString(),
                        maxLines = 1, // Limita el texto a una sola línea
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis // Trunca el texto si no cabe en una línea
                    )
                }
            }
        }
    }
}
