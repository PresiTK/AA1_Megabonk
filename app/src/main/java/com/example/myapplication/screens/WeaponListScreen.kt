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

data class Weapon(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

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
            Text(
                text = "Weapon List",
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
            Image(
                painter = painterResource(id = weapon.imageResId),
                contentDescription = weapon.name,
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
                    text = weapon.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

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
