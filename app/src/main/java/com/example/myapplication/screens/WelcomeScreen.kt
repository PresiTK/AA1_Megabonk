package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Welcome to BonkAPIM",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        Box {
                            IconButton(onClick = { expanded = true }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Comps List") },
                                    onClick = {
                                        expanded = false
                                        navController.navigate("comps_list")
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Item List") },
                                    onClick = {
                                        expanded = false
                                        navController.navigate("item_list")
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Character List") },
                                    onClick = {
                                        expanded = false
                                        navController.navigate("character_list")
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Weapon List") },
                                    onClick = {
                                        expanded = false
                                        navController.navigate("weapon_list")
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Tomes List") },
                                    onClick = {
                                        expanded = false
                                        navController.navigate("tomes_list")
                                    }
                                )
                            }
                        }
                    }
                )
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

