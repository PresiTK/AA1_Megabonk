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

data class Weapon(
    val name: String,
    val description: String,
    val imageName: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    //Datos de armas
    val weapons = listOf(
        Weapon(
            name = "Axe",
            description = "Throw spinning axes that deal area damage."
        ),
        Weapon(
            name = "Aura",
            description = "Emit a powerful aura that damages all nearby enemies continuously."
        ),
        Weapon(
            name = "Lightning Staff",
            description = "Channel lightning bolts that strike random enemies with devastating power."
        ),
        Weapon(
            name = "Fire Sword",
            description = "A legendary blade engulfed in eternal flames, leaving a trail of fire in its wake."
        ),
        Weapon(
            name = "Ice Bow",
            description = "Shoots arrows that freeze enemies on impact, slowing their movement speed."
        )
    )

    // Filtrar armas según la búsqueda
    val filteredWeapons = if (searchText.isEmpty()) {
        weapons
    } else {
        weapons.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weapon List") },
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
                text = "Weapon List",
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

            // Lista de armas
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredWeapons) { weapon ->
                    WeaponCard(weapon = weapon)
                }
            }
        }
    }
}

@Composable
fun WeaponCard(weapon: Weapon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to weapon details */ }
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

            // Información del arma
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Nombre del arma
                Text(
                    text = weapon.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Descripción del arma
                Text(
                    text = weapon.description,
                    fontSize = 14.sp,
                    color = Color.Black,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
