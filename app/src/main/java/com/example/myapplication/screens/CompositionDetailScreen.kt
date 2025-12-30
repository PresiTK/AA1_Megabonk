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
    //Controlador para ir hacia otras pantallas o volver a la pantalla anterior
    navController: NavController,
    //Nombre de la composicion que estamos mostrando
    compositionName: String,
    //Mostramos el tier al que pertenece la composicion
    tier: String
) {
    //Hacemos un scaffold para mostrar los componentes por pantalla organizados como queremos
    Scaffold(
        topBar = {
            //Bara superior de la pantalla
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
        //Nos asseguramos de que el contenido quede por detras de la barra si la deslizamos
    ) { paddingValues ->
        //Añadimos la columna para que este todo centrado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            //Titulo de la composicion
            Text(
                text = compositionName,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            //Creamos un card para que se distinga la informacion del fondo
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
                    //Creamos una hilera para que este en linea la letra y la imagen
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Letra de que tier es
                        Text(
                            text = tier,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(end = 16.dp)
                        )

                        //PlaceHolder para la imagen de la composicion
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.White)
                                .border(1.dp, Color.Black)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        //Texto Items para que el jugador sepa que es lo que va a ver debajo
                        Text(
                            text = "Items",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    //Creamos una cuadricula 3 x 6 para poner las imagenes de los items correctamente y organizadas
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(6),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        //Creamos las 18 casillas que tendremos para añadir todos los items
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
//Funcion para definir la forma que tendra la casilla de la imagen de cada slot de la cuadricula
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
    ) {    }
}