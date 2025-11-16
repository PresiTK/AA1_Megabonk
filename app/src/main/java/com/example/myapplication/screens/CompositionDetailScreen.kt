package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompositionDetailScreen(
    navController: NavController,
    compositionName: String,
    tier: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(compositionName) },
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
            // Título con el nombre de la composición
            Text(
                text = compositionName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Sección principal con fondo gris y borde negro
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEEEEEE)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Fila superior: Tier letter + imagen de composición + "Items"
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Letra del tier
                        Text(
                            text = tier,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(end = 16.dp)
                        )

                        // Cuadrado blanco (placeholder para imagen de composición)
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.White)
                                .border(1.dp, Color.Black)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        // Texto "Items"
                        Text(
                            text = "Items",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // Cuadrícula de ítems (3 filas x 6 columnas = 18 cuadrados)
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(6),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(18) { index ->
                            ItemSlot(index = index)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemSlot(index: Int) {
    Box(
        modifier = Modifier
            .size(55.dp)
            .background(Color.White)
            .border(1.dp, Color.Black)
            .clickable {
                // TODO: Agregar/ver ítem
            },
        contentAlignment = Alignment.Center
    ) {
        // Placeholder - aquí se cargaría la imagen del ítem desde drawable
        // Image(painter = painterResource(id = R.drawable.item_image), ...)
    }
}