package com.example.jetpacklistlazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.widget.ThemeUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter

import com.example.jetpacklistlazycolumn.ui.theme.JetpackListLazyColumnTheme
import com.example.jetpacklistlazycolumn.ui.theme.ScreenChange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackListLazyColumnTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.Black) {
                    NavigationHost()
                }
            }
        }
    }
}

fun getLista(): List<Characters> {
    return listOf(
        Characters(
            "Monkey D. Luffy"+"\n",
            "Fruta Gomu Gomu"+"\n",
            "1.500.000.000",
            "Luffy es el capitán de los sombreros de paja, sus hermanos son Sabo y Ace y su padre Monkey D. Dragon." +
                    "Pertenece a los de la peor generación y se ha ganado la reputación como otro emperador del mar.",
            "https://www.fifteenlovers.com/wp-content/uploads/2021/03/monkey-d-luffy-quotes.png",
            "https://wallpapercave.com/wp/wp2080976.jpg"
        ),
        Characters(
            "Sabo"+"\n",
            "Fruta Mera Mera"+"\n",
            "602.000.000",
            "Sabo es el jefe de personal del ejército revolucionario y hermano de Luffy, trabaja junto a su padre." +
                    "Cuando murió Ace él tomó su fruta y obtuvo sus poderes.",
            "https://i.pinimg.com/originals/a6/a6/77/a6a6776b550978230e55ccf5036d5c8c.jpg",
            "https://i.pinimg.com/originals/a6/a6/77/a6a6776b550978230e55ccf5036d5c8c.jpg"
        ),
        Characters(
            "Ace"+"\n",
            "Fruta Mera Mera"+"\n",
            "550.000.000",
            "Ace fue comandante de los piratas de Barbablanca, murió en la guerra de Marineford a manos de un almirante." +
                    "Era hermano de Luffy y de Sabo e hijo de Gol D. Roger, el rey de los piratas.",
            "https://tierragamer.com/wp-content/uploads/2020/09/Ace-One-Piece-Manga.jpg",
            "https://cdn.atomix.vg/wp-content/uploads/2020/09/one-piece.jpg"
        ),
        Characters(
            "Gold D. Roger"+"\n",
            "Sin fruta, fuerza bruta"+"\n",
            "5.564.800.000",
            "En su día fue el rey de los piratas tras haber encontrado el mayor tesoro que jamás haya existido, el One Piece." +
                    "Murió ejecutado a manos de la marina no sin antes dejar claro que el One Piece existe de verdad.",
            "https://laverdadnoticias.com/__export/1614110298246/sites/laverdad/img/2021/02/23/gol_d_roger_one_piece_anime.jpg_673822677.jpg",
            "https://laverdadnoticias.com/__export/1614110298246/sites/laverdad/img/2021/02/23/gol_d_roger_one_piece_anime.jpg_673822677.jpg"
        ),
        Characters(
            "Monkey D. Dragon"+"\n",
            "Fruta desconocida"+"\n",
            "Recompensa incalculable",
            "Considerado como el peor criminal del mundo, Dragon es el comandante supremo del ejercito revolucionario." +
                    "Luffy es su hijo y lucha contra el gobierno mundial para un mundo más justo y libre.",
            "https://static0.cbrimages.com/wordpress/wp-content/uploads/2020/06/Monkey-D-Dragon-Featured.jpg",
            "https://static0.cbrimages.com/wordpress/wp-content/uploads/2020/06/Monkey-D-Dragon-Featured.jpg"
        )
    )
}

@Composable
fun inicio(navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.onSurface,
                        MaterialTheme.colors.secondaryVariant,
                    )
                )
            )
    )
    {
        items(getLista()) { characters ->
            Box(
                modifier = Modifier

                    .clickable(onClick = {
                        navController.navigate(
                            ScreenChange.chars.changeto(
                                characters.name
                            )
                        )
                    }
                    )
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colors.onSurface,
                                MaterialTheme.colors.secondaryVariant,
                            )
                        )
                    )
                    .padding(horizontal = 1.dp, vertical = 2.dp)
                    .fillMaxSize()
            )
            {
                CargarImagen(url = characters.image2)
            }
        }
    }
}

@Composable
fun info(Nombre: String, navController: NavController) {
    val personajes = getLista()
    var selectedChar = Characters("","","","","", "")

    for (c in personajes) {
        if (c.name == Nombre) {
            selectedChar = c
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.onBackground,
                        MaterialTheme.colors.error,
                    )))
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(14.dp)
    ) {

        Row {
            Text(
                text = selectedChar.name,
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 2.em,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .width(200.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(30.dp))
        ) {
            CargarImagenCompleta(url = selectedChar.image)
        }

        Row {
            Text(
                text =  selectedChar.bounty,
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 2.em
            )
        }

        Row {
            Text(
                text = selectedChar.fruit.toString(),
                color = Color.White,
                fontSize = 20.sp,
                lineHeight = 2.em,
                textAlign = TextAlign.Center
            )
        }

        Row {
            Text(
                text = selectedChar.description,
                color = Color.White,
                lineHeight = 2.em,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { navController.navigate(ScreenChange.home.screen) },
            modifier = Modifier
                .padding(top = 15.dp)
                .background(Color.Blue)
        ) {
            Text(text = "VOLVER ATRÁS")
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenChange.home.screen) {
        composable(ScreenChange.home.screen) {
            inicio(navController = navController)
        }

        composable(ScreenChange.chars.screen) { navBackStackEntry ->
            var nombre = navBackStackEntry.arguments?.getString("name")
            info(nombre!!, navController = navController)
        }
    }
}

@Composable
fun CargarImagen(url: String) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = "imagen",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .padding(1.dp)
            .height(120.dp)
    )
}
@Composable
fun CargarImagenCompleta(url: String) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = "imagen",
        modifier = Modifier
            .height(220.dp)
            .width(220.dp)
            .padding(0.dp),
        contentScale = ContentScale.Crop
    )
}