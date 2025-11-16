package com.example.myapplication.screens


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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Character(
    val name: String,
    val description: String,
    val imageName: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    // Datos de ejemplo - puedes reemplazar con tus datos reales
    val characters = listOf(
        Character(
            name = "Monke",
            description = "A mysterious monkey with incredible agility and banana-throwing skills."
        ),
        Character(
            name = "Calcium",
            description = "Strong bones, strong will. This skeleton warrior never gives up."
        ),
        Character(
            name = "Sir Oofie",
            description = "No one knows where he got the shades. No one asks."
        ),
        Character(
            name = "Dark Knight",
            description = "A fallen hero who now fights for redemption in the shadows."
        ),
        Character(
            name = "Wizard Pete",
            description = "Master of arcane arts with a peculiar fondness for cheese."
        )
    )

    // Filtrar personajes según la búsqueda
    val filteredCharacters = if (searchText.isEmpty()) {
        characters
    } else {
        characters.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character List") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Título
            Text(
                text = "Character List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Barra de búsqueda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color(0xFFEEEEEE))
                    .border(1.dp, Color.Black)
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }

            // Lista de personajes
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
            // Cuadrado blanco (placeholder para imagen)
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Black)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información del personaje
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Nombre del personaje
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Descripción del personaje
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
