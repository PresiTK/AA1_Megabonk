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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

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
    // Obtener el contexto para acceder a los recursos
    val context = LocalContext.current
    val resources = context.resources

    // Cargar las dimensiones desde el XML
    val paddingMedium = resources.getDimension(R.dimen.padding_medium).dp
    val spacingMedium = resources.getDimension(R.dimen.spacing_medium).dp
    val imageSizeMedium = resources.getDimension(R.dimen.image_size_medium).dp
    val itemSlotSize = resources.getDimension(R.dimen.item_slot_size).dp
    val borderWidth = resources.getDimension(R.dimen.border_width).dp
    val textSizeLarge = resources.getDimension(R.dimen.text_size_large).sp
    val textSizeExtraLarge = resources.getDimension(R.dimen.text_size_extra_large).sp
    val textSizeHuge = resources.getDimension(R.dimen.text_size_huge).sp

    // Cargar los colores desde el XML
    val primaryLight = colorResource(id = R.color.primary_light)
    val onPrimaryLight = colorResource(id = R.color.on_primary_light)
    val backgroundLight = colorResource(id = R.color.background_light)
    val onBackgroundLight = colorResource(id = R.color.on_background_light)
    val surfaceContainerLight = colorResource(id = R.color.surface_container_light)
    val surfaceContainerHighLight = colorResource(id = R.color.surface_container_high_light)
    val onSurfaceLight = colorResource(id = R.color.on_surface_light)
    val outlineLight = colorResource(id = R.color.outline_light)

    //Hacemos un scaffold para mostrar los componentes por pantalla organizados como queremos
    Scaffold(
        topBar = {
            //Bara superior de la pantalla
            Surface(
                color = primaryLight
            ) {
                TopAppBar(
                    title = {
                        Text(
                            compositionName,
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
        //Nos asseguramos de que el contenido quede por detras de la barra si la deslizamos
    ) { paddingValues ->
        //Añadimos la columna para que este todo centrado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(paddingMedium)
        ) {
            //Titulo de la composicion
            Text(
                text = compositionName,
                fontSize = textSizeExtraLarge,
                fontWeight = FontWeight.Bold,
                color = onBackgroundLight,
                modifier = Modifier.padding(bottom = paddingMedium)
            )

            //Creamos un card para que se distinga la informacion del fondo
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(borderWidth, outlineLight),
                colors = CardDefaults.cardColors(
                    containerColor = surfaceContainerLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(paddingMedium)
                ) {
                    //Creamos una hilera para que este en linea la letra y la imagen
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = paddingMedium),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Letra de que tier es
                        Text(
                            text = tier,
                            fontSize = textSizeHuge,
                            fontWeight = FontWeight.Bold,
                            color = onSurfaceLight,
                            modifier = Modifier.padding(end = paddingMedium)
                        )

                        //PlaceHolder para la imagen de la composicion
                        Box(
                            modifier = Modifier
                                .size(imageSizeMedium)
                                .background(surfaceContainerHighLight)
                                .border(borderWidth, outlineLight)
                        )

                        Spacer(modifier = Modifier.width(paddingMedium))

                        //Texto Items para que el jugador sepa que es lo que va a ver debajo
                        Text(
                            text = "Items",
                            fontSize = textSizeLarge,
                            fontWeight = FontWeight.Bold,
                            color = onSurfaceLight
                        )
                    }

                    //Creamos una cuadricula 3 x 6 para poner las imagenes de los items correctamente y organizadas
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(6),
                        horizontalArrangement = Arrangement.spacedBy(spacingMedium),
                        verticalArrangement = Arrangement.spacedBy(spacingMedium),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        //Creamos las 18 casillas que tendremos para añadir todos los items
                        items(18) { index ->
                            ItemSlot(
                                index = index,
                                itemSlotSize = itemSlotSize,
                                borderWidth = borderWidth,
                                surfaceContainerHighLight = surfaceContainerHighLight,
                                outlineLight = outlineLight
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
//Funcion para definir la forma que tendra la casilla de la imagen de cada slot de la cuadricula
fun ItemSlot(
    index: Int,
    itemSlotSize: androidx.compose.ui.unit.Dp,
    borderWidth: androidx.compose.ui.unit.Dp,
    surfaceContainerHighLight: Color,
    outlineLight: Color
) {
    Box(
        modifier = Modifier
            .size(itemSlotSize)
            .background(surfaceContainerHighLight)
            .border(borderWidth, outlineLight)
            .clickable {
                // TODO: Agregar/ver ítem
            },
        contentAlignment = Alignment.Center
    ) {    }
}