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

data class Item(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    val items = listOf(
        Item(
            name = "Moldy Cheese",
            description = "+40% chance to poison enemies on hit.",
            imageResId = R.drawable.moldy_cheese

        ),
        Item(
            name = "Brass Knuckles",
            description = "+15 damage to melee attacks.",
            imageResId = R.drawable.brass_knuckles

        ),
        Item(
            name = "Overpowered Lamp",
            description = "Illuminates dark areas and deals 50 damage to enemies nearby.",
            imageResId = R.drawable.lamp

        ),
        Item(
            name = "Borgar",
            description = "+2% chance to spawn Borgar upon killing an enemy.",
            imageResId = R.drawable.borgar

        ),
        Item(
            name = "Clover",
            description = "Increase Luck by +7.5%.",
            imageResId = R.drawable.clover
        ),
        Item(
            name = "Gym Sauce",
            description = "Increase Damage by +10%.",
            imageResId = R.drawable.gym_sauce
        ),
        Item(
            name = "Ghost",
            description = "Using an interactable summons ghosts.",
            imageResId = R.drawable.ghost
        ),
        Item(
            name = "Campfire",
            description = "Standing still heals you.",
            imageResId = R.drawable.campfire
        ),
        Item(
            name = "Slurp Gloves",
            description = "Cast blood magic upon hitting an enemy, damaging nearby enemies and healing you for 7.5%. 9s cooldown.",
            imageResId = R.drawable.slurp_gloves
        ),
        Item(
            name = "Mirror",
            description = "Reflect any incoming damage back to the attacker, and gain a short invulnerability.",
            imageResId = R.drawable.mirror
        ),
        Item(
            name = "Bob (Dead)",
            description = "For every 14 units moved, summon Bob (he's dead).",
            imageResId = R.drawable.bob
        ),
        Item(
            name = "Backpack",
            description = "+1 Projectile Count for all Weapons.",
            imageResId = R.drawable.backpack
        ),
    )

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
            Text(
                text = "Item List",
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

            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.name,
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
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }

}

