package com.example.myapplication.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Funcion para declarar la pantalla principal
fun HomeScreen(navController: NavController) {

    //Añadimos un Scaffold para mostrar los componentes de la pantalla organizados
    Scaffold(
        topBar = {
            Column {
                //Barra superior
                TopAppBar(
                    title = {
                        Text(
                            text = "BonkAPIM",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
                //Creamos una barra que divide la barra superior del contenido que hay debajo
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
        //Nos asseguramos de que el contenido quede por detras de la barra si la deslizamos
    ) { paddingValues ->
        //Añadimos un box para posicionar los elementos de la pantalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.BottomCenter
        ) {
            //Boton de login para poder entrar a la app
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .width(200.dp)
                    .height(56.dp)
            ) {
                //Texto que dice que hace el boton de login
                Text(
                    text = "Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
