package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Screen

data class Composition(
    val name: String,
    val tier: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompsListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    var expandedTiers by remember { mutableStateOf(setOf<String>()) }

    // Datos de ejemplo - puedes reemplazar con tus datos reales
    val compositions = listOf(
        Composition("Nombre de la composicion", "S"),
        Composition("Nombre de la composicion", "A"),
        Composition("Nombre de la composicion", "B"),
        Composition("Nombre de la composicion", "C"),
        Composition("Nombre de la composicion", "D")
    )

    val tiers = listOf("S", "A", "B", "C", "D")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comps List") },
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
                text = "MegaBonk Comps Tier List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Sección de búsqueda
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Nombre de la composicion",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Lista de tiers
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tiers) { tier ->
                    val isExpanded = expandedTiers.contains(tier)
                    val compsInTier = compositions.filter { it.tier == tier }

                    TierRow(
                        tier = tier,
                        compositions = compsInTier,
                        isExpanded = isExpanded,
                        onToggle = {
                            expandedTiers = if (isExpanded) {
                                expandedTiers - tier
                            } else {
                                expandedTiers + tier
                            }
                        },
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun TierRow(
    tier: String,
    compositions: List<Composition>,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE))
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Letra del tier
            Text(
                text = tier,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(end = 16.dp)
            )

            // Cuadrado blanco (placeholder para imagen) + Nombre - clickeable para navegar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        val firstComp = compositions.firstOrNull()
                        if (firstComp != null) {
                            navController.navigate(
                                Screen.CompositionDetail.createRoute(firstComp.name, firstComp.tier)
                            )
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White)
                        .border(1.dp, Color.Gray)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Nombre de la composición
                Text(
                    text = compositions.firstOrNull()?.name ?: "Nombre de la composicion",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Icono de expandir/colapsar
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Colapsar" else "Expandir",
                modifier = Modifier.clickable { onToggle() }
            )
        }

        // Contenido expandido (para futuras composiciones)
        if (isExpanded && compositions.size > 1) {
            Spacer(modifier = Modifier.height(8.dp))
            compositions.drop(1).forEach { comp ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 48.dp, top = 8.dp)
                        .clickable {
                            navController.navigate(
                                Screen.CompositionDetail.createRoute(comp.name, comp.tier)
                            )
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color.White)
                            .border(1.dp, Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = comp.name,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
