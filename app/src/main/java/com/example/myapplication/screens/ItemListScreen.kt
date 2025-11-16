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

data class Item(
    val name: String,
    val description: String,
    val imageName: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    // Datos de ejemplo - puedes reemplazar con tus datos reales
    val items = listOf(
        Item(
            name = "Moldy Cheese",
            description = "+40% chance to poison enemies on hit."
        ),
        Item(
            name = "Brass Knuckles",
            description = "+15 damage to melee attacks."
        ),
        Item(
            name = "Overpowered Lamp",
            description = "Illuminates dark areas and deals 50 damage to enemies nearby."
        ),
        Item(
            name = "Magic Potion",
            description = "Restores 100 HP instantly."
        ),
        Item(
            name = "Ancient Scroll",
            description = "+25% XP gain from all sources."
        )
    )

    // Filtrar ítems según la búsqueda
    val filteredItems = if (searchText.isEmpty()) {
        items
    } else {
        items.filter { it.name.contains(searchText, ignoreCase = true) }
    }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Título
            Text(
                text = "Item List",
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
                    .border(1.dp, Color.Gray)
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

            // Lista de ítems
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
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to item details */ }
            .border(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEEEEE)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Cuadrado blanco (placeholder para imagen)
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.White)
                    .border(1.dp, Color.Gray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información del ítem
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Nombre del ítem
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Descripción del ítem
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}
