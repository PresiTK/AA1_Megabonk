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

//Esta data class la utilizamos para hacer la plantilla de los characters tiene su nombre, la descripcion del personaje, la arma inicial que tiene el personaje
//y en imageResId es donde ponemos el drawable que es la imagen del personaje
data class Character(
    val name: String,
    val initialWeapon: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Esta funcion es la encargada de printear por pantalla la lista de characters
fun CharacterListScreen(navController: NavController) {
    //Esta variable guarda el texto de la barra de busqueda
    var searchText by remember { mutableStateOf("") }

    //Esto es la lista de characters que se basa en la data class que es la plantilla hecha anteriormente
    val characters = listOf(
        Character(
            name = "Monke",
            initialWeapon = "Banana",
            description = "Climb up walls. Also gain +2 Max HP per level.",
            imageResId = R.drawable.monke

        ),
        Character(
            name = "Calcium",
            initialWeapon = "Bone",
            description = "Speed builds up over time, but is halved when you take damage. Also gain damage based on speed.",
            imageResId = R.drawable.calcium
        ),
        Character(
            name = "Sir Oofie",
            initialWeapon = "Sword",
            description = "Gain 1% Armor per level.",
            imageResId = R.drawable.siroofie
        ),
        Character(
            name = "Bandit",
            initialWeapon = "Dexecutioner",
            description = "Gain 1% Attack Speed per level.",
            imageResId = R.drawable.bandit
        ),
        Character(
            name = "Megachad",
            initialWeapon = "Aura",
            description = "Flex to stop damage. Cooldown -0.2s per level. Gain 2.5% Damage when flexing.",
            imageResId = R.drawable.megachad
        ),
        Character(
            name = "Fox",
            initialWeapon = "Firestaff",
            description = "Gain 2% Luck per level.",
            imageResId = R.drawable.fox
        ),
        Character(
            name = "Ogre",
            initialWeapon = "Axe",
            description = "Gain 1.5% Damage per level.",
            imageResId = R.drawable.ogre
        ),
        Character(
            name = "CL4NK",
            initialWeapon = "Rvolver",
            description = "Gain 1% Crit Chance per level.",
            imageResId = R.drawable.cl4nk
        )
    )

    //En este if que se iguala en la veriable filtramos los personajes segun el texto que hemos ingresado en la variable searchText
    val filteredCharacters = if (searchText.isEmpty()) {
        characters
    } else {
        characters.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    //Definimos la estructura que tendra la pantalla del mobil de esta screen
    Scaffold(
        topBar = {
            //La barra superior
            TopAppBar(
                title = { Text("Character List") },
                navigationIcon = {
                    //Boton de volver atras
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
        //Definimos un column para que este centrado todo igual
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //Título
            Text(
                text = "Character List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //Barra de búsqueda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color(0xFFEEEEEE))
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
            ) {
                //Barra de texto para la busqueda de personajes
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            //Lista de personajes
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredCharacters) { character ->
                    CharacterCard(character = character)
                }
            }
        }
    }
}

@Composable
//Funcion para dibujar la tarjeta con la informacion del personaje detallada anteriormente
fun CharacterCard(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to character details */ }
            .border(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEEEEE)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            //Imagen del personaje
            Image(
                painter = painterResource(id = character.imageResId),
                contentDescription = character.name,
                modifier = Modifier
                    .size(90.dp)
                    .border(1.dp, Color.Black),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            //Texto del arma inicial
            Text(
                text = "Initial Weapon: ",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
            Text(
                text = character.initialWeapon,
                fontSize = 13.sp,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Medium
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                //Nombre del personaje
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                //Descripción del personaje
                Text(
                    text = character.description,
                    fontSize = 14.sp,
                    color = Color.Black,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
