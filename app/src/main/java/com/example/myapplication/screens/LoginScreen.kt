package com.example.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Funcion para declarar la login screen
fun LoginScreen(navController: NavController) {
    // Obtener el contexto para acceder a los recursos
    val context = LocalContext.current
    val resources = context.resources

    // Cargar las dimensiones desde el XML
    val paddingMedium = resources.getDimension(R.dimen.padding_medium).dp
    val paddingLarge = resources.getDimension(R.dimen.padding_large).dp
    val buttonHeight = resources.getDimension(R.dimen.button_height).dp
    val textSizeMedium = resources.getDimension(R.dimen.text_size_medium).sp
    val textSizeLarge = resources.getDimension(R.dimen.text_size_large).sp

    // Cargar los colores desde el XML
    val primaryLight = colorResource(id = R.color.primary_light)
    val onPrimaryLight = colorResource(id = R.color.on_primary_light)
    val backgroundLight = colorResource(id = R.color.background_light)
    val onSurfaceLight = colorResource(id = R.color.on_surface_light)
    val onSurfaceVariantLight = colorResource(id = R.color.on_surface_variant_light)
    val outlineLight = colorResource(id = R.color.outline_light)
    val tertiaryLight = colorResource(id = R.color.tertiary_light)

    //Creamos dos variables que almacenaran el correo y la password introducidos por el usuario
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //Creamos la estructura de la pantalla para la hora de mostrarlo por pantalla este organizado
    Scaffold(
        topBar = {
            Surface(
                color = primaryLight
            ) {
                TopAppBar(
                    title = {
                        Text(
                            "Login",
                            color = onPrimaryLight
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = onPrimaryLight
                            )
                        }
                    }
                )
            }
        },
        containerColor = backgroundLight
        //Nos asseguramos de que el contenido no quede por detras de la barra superior
    ) { paddingValues ->
        //Creamos un column para que este todo en una columna
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Creamos el campo de texto para el correo
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        "Correo electrónico",
                        color = onSurfaceVariantLight
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryLight,
                    unfocusedBorderColor = outlineLight,
                    focusedTextColor = onSurfaceLight,
                    unfocusedTextColor = onSurfaceLight
                )
            )

            //Creamos el campo de texto para la contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        "Contraseña",
                        color = onSurfaceVariantLight
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryLight,
                    unfocusedBorderColor = outlineLight,
                    focusedTextColor = onSurfaceLight,
                    unfocusedTextColor = onSurfaceLight
                )
            )

            //Añadimos un texto que enlazara con la pantalla del register
            Text(
                text = "register",
                color = tertiaryLight,
                fontSize = textSizeMedium,
                modifier = Modifier
                    .padding(bottom = paddingLarge - paddingMedium)
                    .clickable { navController.navigate("register") }
            )

            //Creamos este boton para que una vez hecho el login se pueda completar al pulsarlo
            Button(
                onClick = { navController.navigate("welcome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = onPrimaryLight
                )
            ) {
                //Le añadimos un texto al boton para que el usuario sepa que es el boton para hacer el login
                Text(
                    text = "Login",
                    fontSize = textSizeLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}