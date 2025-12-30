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

//Esta data class la utilizamos como base de las composiciones del juego guardando el nombre y el tier
data class Composition(
    val name: String,
    val tier: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Funcion para crear las composiciones
fun CompsListScreen(navController: NavController) {
    //Variables para guardar el texto de la barra de busqueda y los tiers expandidos
    var searchText by remember { mutableStateOf("") }
    var expandedTiers by remember { mutableStateOf(setOf<String>()) }

    //Lista de composiciones
    val compositions = listOf(
        Composition("Monke Best Composition", "S"),
        Composition("Bandit Advanced Composition", "A"),
        Composition("Calcium Standard Composition", "B"),
        Composition("Ogre Normal Composition", "C"),
        Composition("Fox Worst Composition", "D")
    )

    //Lista de tiers
    val tiers = listOf("S", "A", "B", "C", "D")

    //Structura de la screen
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
        //Nos asseguramos de que el contenido quede por detras de la barra si la deslizamos
    ) { paddingValues ->
        //Añadimos un column para que este centrado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //Titulo de la pantalla
            Text(
                text = "MegaBonk Comps Tier List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Cuadro de texto de busqueda
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                //Texto del campo de busqueda
                Text(
                    text = "Nombre de la composicion",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            //Lista de tiers
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tiers) { tier ->
                    val isExpanded = expandedTiers.contains(tier)
                    val compsInTier = compositions.filter { it.tier == tier }

                    //Fila de tiers
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
//Funcion para la fila de tiers
fun TierRow(
    tier: String,
    compositions: List<Composition>,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    navController: NavController
) {
    //Columna para que ocupe todo el ancho
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
            //Letra del tier
            Text(
                text = tier,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(end = 16.dp)
            )

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
                //PlaceHolder de la imagen de la composicion
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.White)
                        .border(1.dp, Color.Gray)
                )

                Spacer(modifier = Modifier.width(16.dp))

                //Nombre de la composición
                Text(
                    text = compositions.firstOrNull()?.name ?: "Nombre de la composicion",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            //Icono para hacer mas grande o mas pequeño el tier
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Colapsar" else "Expandir",
                modifier = Modifier.clickable { onToggle() }
            )
        }

        //Comprobamos si se expande y si es mayor a 1 si es correcto muestra las demas composiciones
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
                    //Nombre de la composicion
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
