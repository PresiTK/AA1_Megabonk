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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

// Data class que representa un arma con su nombre, descripción e imagen
data class Weapon(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    // Lista de todas las armas disponibles del juego
    val weapons = listOf(
        Weapon(
            name = "Axe",
            description = "Throw spinning axes that deal area damage.",
            imageResId = R.drawable.axe
        ),
        Weapon(
            name = "Aura",
            description = "Damage enemies in an area around you.",
            imageResId = R.drawable.aura
        ),
        Weapon(
            name = "Lightning Staff",
            description = "Summons lightning to smite nearby enemies.",
            imageResId = R.drawable.lightning_staff
        ),
        Weapon(
            name = "Dexecutioner",
            description = "A piercing blade. Small chance to instantly execute an enemy.",
            imageResId = R.drawable.dexecutioner
        ),
        Weapon(
            name = "Bananarang",
            description = "Throws bananas that return to the owner.",
            imageResId = R.drawable.bananarang
        )
    )

    // Filtra las armas basándose en el texto de búsqueda del usuario
    val filteredWeapons = if (searchText.isEmpty()) {
        weapons
    } else {
        weapons.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    // Scaffold estructura básica con barraita superior
    Scaffold(
        topBar = {
            // Barritas superior personalizada
            TopAppBar(
                title = {
                    Text(
                        "Weapon List",
                        color = colorResource(id = R.color.on_primary_light)
                    )
                },
                navigationIcon = {
                    // Botón de retroceso en la barra superior
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = colorResource(id = R.color.on_primary_light)
                        )
                    }
                },
                // Colores personalizados de la TopAppBar
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.primary_light),
                    titleContentColor = colorResource(id = R.color.on_primary_light),
                    navigationIconContentColor = colorResource(id = R.color.on_primary_light)
                )
            )
        }
    ) { paddingValues ->
        // Columna principal que contiene toda la interfaz
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background_light))
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = "Weapon List",
                fontSize = dimensionResource(id = R.dimen.text_size_extra_large).value.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.on_background_light),
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
            )

            // Box contenedor del campo de búsqueda con borde y fondo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                    .background(colorResource(id = R.color.surface_container_light))
                    .border(
                        dimensionResource(id = R.dimen.border_width),
                        colorResource(id = R.color.outline_light)
                    )
                    .padding(dimensionResource(id = R.dimen.spacing_small))
            ) {
                // Campo de texto para buscar armas
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it }, // Actualiza el estado al escribir
                    label = { Text("Buscar") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true, // Solo una línea de texto
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(id = R.color.primary_light),
                        unfocusedBorderColor = colorResource(id = R.color.outline_light),
                        focusedLabelColor = colorResource(id = R.color.primary_light),
                        unfocusedLabelColor = colorResource(id = R.color.on_surface_variant_light)
                    )
                )
            }

            // LazyColumn para mostrar la lista de armas
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_large)) // 12dp entre items
            ) {
                // Itera sobre las armas filtradas y crea un WeaponCard para cada una
                items(filteredWeapons) { weapon ->
                    WeaponCard(weapon = weapon)
                }
            }
        }
    }
}

// Composable que representa una tarjeta individual de arma
@Composable
fun WeaponCard(weapon: Weapon) {
    // Card es un contenedor con esquinas redondeadas
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to weapon details */ }
            .border(
                dimensionResource(id = R.dimen.border_width),
                colorResource(id = R.color.outline_light)
            ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.surface_container_light)
        )
    ) {
        // Row organiza la imagen y el texto horizontalmente
        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.spacing_large))
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Imagen del arma
            Image(
                painter = painterResource(id = weapon.imageResId),
                contentDescription = weapon.name,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size_large))
                    .border(
                        dimensionResource(id = R.dimen.border_width),
                        colorResource(id = R.color.outline_light)
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))

            // Column para organizar verticalmente
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Nombre del arma
                Text(
                    text = weapon.name,
                    fontSize = dimensionResource(id = R.dimen.text_size_large).value.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.on_surface_light)
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_small)))

                // Descripción del arma
                Text(
                    text = weapon.description,
                    fontSize = dimensionResource(id = R.dimen.text_size_small).value.sp,
                    color = colorResource(id = R.color.on_surface_variant_light),
                    lineHeight = 20.sp
                )
            }
        }
    }
}