package com.example.myapplication.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    // Obtener el contexto para acceder a los recursos
    val context = LocalContext.current
    val resources = context.resources

    // Cargar las dimensiones desde el XML
    val paddingMedium = resources.getDimension(R.dimen.padding_medium).dp
    val paddingLarge = resources.getDimension(R.dimen.padding_large).dp
    val buttonHeight = resources.getDimension(R.dimen.button_height).dp
    val textSizeLarge = resources.getDimension(R.dimen.text_size_large).sp

    // Estados para almacenar los valores de cada campo del formulario y remeber mas mutableOf para que se mantenga la ui
    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    //Scafold para tener barrita superior bien organizado
    Scaffold(
        topBar = {
            //barrita superior
            TopAppBar(
                title = { Text("Register") },
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
                .padding(horizontal = paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Campo de texto para el email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium),
                singleLine = true
            )

            // Campo de texto para nombre completo
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Nombre y apellido") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium),
                singleLine = true
            )

            // Campo de texto para el apodo
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Apodo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium),
                singleLine = true
            )

            // Campo de texto para la contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )

            // Campo de texto para confirmar la contraseña
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar contraseña") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium * 1.5f),
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )

            // Botón de registro
            Button(
                onClick = { navController.navigate("welcome") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight)
            ) {
                Text(
                    text = "Register",
                    fontSize = textSizeLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}