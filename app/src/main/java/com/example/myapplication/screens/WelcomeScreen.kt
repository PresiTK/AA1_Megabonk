package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    // Scaffold estructura básica con barraita superior
    Scaffold(
        topBar = {
            Column {
                // Barritas superior con título y menú de navegación
                TopAppBar(
                    title = {
                        Text(
                            text = "Welcome to BonkAPIM",
                            fontSize = dimensionResource(id = R.dimen.text_size_extra_large).value.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.on_primary_light)
                        )
                    },
                    actions = {
                        // Menú desplegable con navegación a diferentes pantallas
                        Box {
                            IconButton(onClick = { expanded = true }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = colorResource(id = R.color.on_primary_light)
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
                    },
                    modifier = Modifier.background(colorResource(id = R.color.primary_light))
                )
                // Línea divisoria debajo de la barra superior
                HorizontalDivider(
                    thickness = dimensionResource(id = R.dimen.divider_thickness),
                    color = colorResource(id = R.color.outline_light)
                )
            }
        }
    ) { paddingValues ->
        // Contenedor principal centrado con mensaje de bienvenida
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background_light))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome!",
                fontSize = dimensionResource(id = R.dimen.text_size_huge).value.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.on_background_light)
            )
        }
    }
}