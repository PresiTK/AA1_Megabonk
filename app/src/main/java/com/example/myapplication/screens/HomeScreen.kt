package com.example.myapplication.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Funcion para declarar la pantalla principal
fun HomeScreen(navController: NavController) {
    // Obtener el contexto para acceder a los recursos
    val context = LocalContext.current
    val resources = context.resources

    // Cargar las dimensiones desde el XML
    val paddingExtraLarge = resources.getDimension(R.dimen.padding_extra_large).dp
    val buttonHeight = resources.getDimension(R.dimen.button_height).dp
    val buttonWidthStandard = resources.getDimension(R.dimen.button_width_standard).dp
    val borderWidth = resources.getDimension(R.dimen.border_width).dp
    val textSizeLarge = resources.getDimension(R.dimen.text_size_large).sp
    val textSizeExtraLarge = resources.getDimension(R.dimen.text_size_extra_large).sp

    // Cargar los colores desde el XML
    val primaryLight = colorResource(id = R.color.primary_light)
    val onPrimaryLight = colorResource(id = R.color.on_primary_light)
    val backgroundLight = colorResource(id = R.color.background_light)
    val outlineLight = colorResource(id = R.color.outline_light)

    //Añadimos un Scaffold para mostrar los componentes de la pantalla organizados
    Scaffold(
        topBar = {
            Column {
                //Barra superior
                Surface(
                    color = primaryLight
                ) {
                    TopAppBar(
                        title = {
                            Text(
                                text = "BonkAPIM",
                                fontSize = textSizeExtraLarge,
                                fontWeight = FontWeight.Bold,
                                color = onPrimaryLight
                            )
                        }
                    )
                }
                //Creamos una barra que divide la barra superior del contenido que hay debajo
                HorizontalDivider(
                    thickness = borderWidth,
                    color = outlineLight
                )
            }
        },
        containerColor = backgroundLight
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
                    .padding(bottom = paddingExtraLarge)
                    .width(buttonWidthStandard)
                    .height(buttonHeight),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryLight,
                    contentColor = onPrimaryLight
                )
            ) {
                //Texto que dice que hace el boton de login
                Text(
                    text = "Login",
                    fontSize = textSizeLarge,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}