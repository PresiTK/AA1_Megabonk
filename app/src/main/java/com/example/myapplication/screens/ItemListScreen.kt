package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

//Definimos la data class como base de los items esta data class contiene el nombre del item, la descripcion y la imagen del item
data class Item(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(navController: NavController) {
    //Variable para guardar el texto de la barra de busqueda
    var searchText by remember { mutableStateOf("") }

    //Lista para la defimicion de los items utilizando la data class definida anteriormente
    val items = listOf(
        Item(
            name = "Moldy Cheese",
            description = "+40% chance to poison enemies on hit.",
            imageResId = R.drawable.moldy_cheese

        ),
        Item(
            name = "Brass Knuckles",
            description = "+15 damage to melee attacks.",
            imageResId = R.drawable.brass_knuckles

        ),
        Item(
            name = "Overpowered Lamp",
            description = "Illuminates dark areas and deals 50 damage to enemies nearby.",
            imageResId = R.drawable.lamp

        ),
        Item(
            name = "Borgar",
            description = "+2% chance to spawn Borgar upon killing an enemy.",
            imageResId = R.drawable.borgar

        ),
        Item(
            name = "Clover",
            description = "Increase Luck by +7.5%.",
            imageResId = R.drawable.clover
        ),
        Item(
            name = "Gym Sauce",
            description = "Increase Damage by +10%.",
            imageResId = R.drawable.gym_sauce
        ),
        Item(
            name = "Ghost",
            description = "Using an interactable summons ghosts.",
            imageResId = R.drawable.ghost
        ),
        Item(
            name = "Campfire",
            description = "Standing still heals you.",
            imageResId = R.drawable.campfire
        ),
        Item(
            name = "Slurp Gloves",
            description = "Cast blood magic upon hitting an enemy, damaging nearby enemies and healing you for 7.5%. 9s cooldown.",
            imageResId = R.drawable.slurp_gloves
        ),
        Item(
            name = "Mirror",
            description = "Reflect any incoming damage back to the attacker, and gain a short invulnerability.",
            imageResId = R.drawable.mirror
        ),
        Item(
            name = "Bob (Dead)",
            description = "For every 14 units moved, summon Bob (he's dead).",
            imageResId = R.drawable.bob
        ),
        Item(
            name = "Backpack",
            description = "+1 Projectile Count for all Weapons.",
            imageResId = R.drawable.backpack
        ),
    )

    //Filtra los items segun el texto guardado en la variable searchText si esta vacia se pueden ver todos
    val filteredItems = if (searchText.isEmpty()) {
        items
    } else {
        items.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    //Definimos la estructura de la pantalla de esta screen
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Item List") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        //Este column centra todo el texto en la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //Titulo de la pantalla
            Text(
                text = "Item List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            //Este es el contenedor de la barra de busqueda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color(0xFFEEEEEE))
                    .border(1.dp, Color.Gray)
                    .padding(4.dp)
            ) {
                //Definimos el contenedor donde se escribe el texto
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            //Lista de items en vertical para su visualizacion en pantalla
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredItems) { item ->
                    ItemCard(item = item)
                }
            }
        }
    }
}

@Composable
//Funcion utilizada para dibujar las tarjetas de cada item
fun ItemCard(item: Item) {
    //Dibujamos las cards en pantalla
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to item details */ }
            .border(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEEEEE)
        )
    ) {
        //Lo ponemos en una fila para que este alineado la imagen y el texto
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Imagen del item
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.name,
                modifier = Modifier
                    .size(90.dp)
                    .border(1.dp, Color.Black),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
            //Hacemos una columna para poder poner las cosas debajo y no quede todo en la misma linea
            Column(
                modifier = Modifier.weight(1f)
            ) {
                //Nombre del item
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))
                //Descripcion del item
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }

}

