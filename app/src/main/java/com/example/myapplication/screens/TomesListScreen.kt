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

data class Tome(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TomesListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

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

    val filteredTomes = if (searchText.isEmpty()) {
        tomes
    } else {
        tomes.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tomes List") },
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
            Text(
                text = "Tomes List",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredTomes) { tome ->
                    TomeCard(tome = tome)
                }
            }
        }
    }
}

@Composable
fun TomeCard(tome: Tome) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to tome details */ }
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
            Image(
                painter = painterResource(id = tome.imageResId),
                contentDescription = tome.name,
                modifier = Modifier
                    .size(90.dp)
                    .border(1.dp, Color.Black),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = tome.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = tome.description,
                    fontSize = 14.sp,
                    color = Color.Black,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
