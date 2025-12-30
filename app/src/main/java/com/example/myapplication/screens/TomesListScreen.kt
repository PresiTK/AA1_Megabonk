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
import androidx.compose.material3.OutlinedTextFieldDefaults
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

// Data class que representa un Tomo con su nombre descripcion y id
data class Tome(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TomesListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    // Lista de todos los tomos disponibles que corresponde a una clase tome
    val tomes = listOf(
        Tome(
            name = "Quantity Tome",
            description = "+Projectile Count - Increases the number of attacks / projectiles.",
            imageResId = R.drawable.quantity_tome
        ),
        Tome(
            name = "Damage Tome",
            description = "+Damage - Increases the base damage dealt by all attacks.",
            imageResId = R.drawable.damage_tome
        ),
        Tome(
            name = "Cooldown Tome",
            description = "-Cooldown - Reduces the time between attacks, allowing for faster combat.",
            imageResId = R.drawable.coldown
        ),
        Tome(
            name = "Size Tome",
            description = "+Size - The size of your attacks, projectiles, explosions, and more.",
            imageResId = R.drawable.size
        ),
        Tome(
            name = "Shield Tome",
            description = "+Shield - Shield takes damage instead of losing HP before it breaks. Shield regenerates quickly after not taking damage for a while",
            imageResId = R.drawable.shield
        )
    )

    // Filtra los tomos basándose en el texto de busqueda que añada el usuario
    val filteredTomes = if (searchText.isEmpty()) {
        tomes
    } else {
        tomes.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    // Scaffold estructura básica con barraita superior
    Scaffold(
        topBar = {
            // Barritas superior personalizada
            TopAppBar(
                title = {
                    Text(
                        "Tomes List",
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
                // Fondo de la barra superior
                modifier = Modifier.background(colorResource(id = R.color.primary_light))
            )
        }
    ) { paddingValues ->
        // Columna principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background_light))
                .padding(paddingValues)
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = "Tomes List",
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
                // Campo de texto para buscar tomos
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

            // LazyColumn para mostrar la lista de tomos
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_large)) // 12dp entre items
            ) {
                // Itera sobre los tomos filtrados y crea un TomeCard para cada uno
                items(filteredTomes) { tome ->
                    TomeCard(tome = tome)
                }
            }
        }
    }
}

// Composable que representa una tarjeta individual de tomo
@Composable
fun TomeCard(tome: Tome) {
    // Card es un contenedor con esquinas redondeadas
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to tome details */ }
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
            // Imagen del tomo
            Image(
                painter = painterResource(id = tome.imageResId),
                contentDescription = tome.name,
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
                // Nombre del tomo
                Text(
                    text = tome.name,
                    fontSize = dimensionResource(id = R.dimen.text_size_large).value.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.on_surface_light)
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_small)))

                // Descripción del tomo
                Text(
                    text = tome.description,
                    fontSize = dimensionResource(id = R.dimen.text_size_small).value.sp,
                    color = colorResource(id = R.color.on_surface_variant_light),
                    lineHeight = 20.sp
                )
            }
        }
    }
}