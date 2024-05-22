package com.example.practica3

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage

@Composable
fun carta(name: String, image: String, premiered: String, language: String, summary: String, genres: String, country: String) {


    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        Box(
            modifier = Modifier
                .height(60.dp)  // Alto de 100dp
                .fillMaxWidth()
        ) {
            val context = LocalContext.current
            val activity = context as? Activity
            IconButton(onClick = { activity?.finish() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, // Puedes usar un ícono predeterminado o personalizado
                    contentDescription = "Atras"
                )
            }
        }
        Row(
            modifier = Modifier
                .height(300.dp)  // Alto de 100dp
            //.width(200.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(300.dp)  // Alto de 100dp
                    .width(200.dp)
                    .padding(15.dp)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(18.dp))
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(text = name, fontSize = 28.sp)
                Text(text = "Genres: $genres", fontSize = 18.sp)
                Text(text = "Premiered: $premiered", fontSize = 18.sp)
                Text(text = "Country: $country", fontSize = 18.sp)
                Text(text = "Language: $language", fontSize = 18.sp)

                val context = LocalContext.current

                IconButton(
                    onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            var textoCompartido = "NAME: ${name} \nSUMMARY:  \n${summary}"
                            putExtra(Intent.EXTRA_TEXT, textoCompartido)
                            type = "text/plain"
                        }
                        val chooser = Intent.createChooser(shareIntent, "Compartir")
                        context.startActivity(chooser)
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.End)
                        .size(50.dp),
                    //.offset(x = (640).dp, y = (-100).dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Share, // Puedes usar un ícono predeterminado o personalizado
                        contentDescription = "Compartir"
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp) // Define la altura de la línea
                .background(Color.Gray) // Define el color de la línea
        )

        convertidorHTML(summary)

    }
}



@Composable
fun convertidorHTML(html: String) {
    val text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
    RichText(text = text)
}

@Composable
fun RichText(text: CharSequence) {
    Text(
        text = buildAnnotatedString {
            append(text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .verticalScroll(rememberScrollState()),

        fontSize = 20.sp // Cambia el tamaño de la fuente aquí
    )
}