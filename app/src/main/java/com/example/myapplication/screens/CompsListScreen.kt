package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.myapplication.Screen

//Esta data class la utilizamos como base de las composiciones del juego guardando el nombre y el tier
data class Composition(
    val name: String,
    val tier: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Funcion para crear las composiciones
fun CompsListScreen(navController: NavController) {
    // Obtener el contexto para acceder a los recursos
    val context = LocalContext.current
    val resources = context.resources

    // Cargar las dimensiones desde el XML
    val paddingMedium = resources.getDimension(R.dimen.padding_medium).dp
    val spacingSmall = resources.getDimension(R.dimen.spacing_small).dp
    val spacingMedium = resources.getDimension(R.dimen.spacing_medium).dp
    val imageSizeSmall = resources.getDimension(R.dimen.image_size_small).dp
    val borderWidth = resources.getDimension(R.dimen.border_width).dp
    val textSizeSmall = resources.getDimension(R.dimen.text_size_small).sp
    val textSizeMedium = resources.getDimension(R.dimen.text_size_medium).sp
    val textSizeExtraLarge = resources.getDimension(R.dimen.text_size_extra_large).sp
    val textSizeHuge = resources.getDimension(R.dimen.text_size_huge).sp

    // Cargar los colores desde el XML
    val primaryLight = colorResource(id = R.color.primary_light)
    val onPrimaryLight = colorResource(id = R.color.on_primary_light)
    val backgroundLight = colorResource(id = R.color.background_light)
    val onBackgroundLight = colorResource(id = R.color.on_background_light)
    val surfaceContainerLight = colorResource(id = R.color.surface_container_light)
    val onSurfaceLight = colorResource(id = R.color.on_surface_light)
    val onSurfaceVariantLight = colorResource(id = R.color.on_surface_variant_light)
    val outlineLight = colorResource(id = R.color.outline_light)
    val surfaceContainerHighLight = colorResource(id = R.color.surface_container_high_light)

    //Variables para guardar el texto de la barra de busqueda y los tiers expandidos
    var searchText by remember { mutableStateOf("") }
    var expandedTiers by remember { mutableStateOf(setOf<String>()) }

    //Lista de composiciones
    val compositions = listOf(
        Composition("Monke Best Composition", "S"),
        Composition("Bandit Advanced Composition", "A"),
        Composition("Calcium Standard Composition", "B"),
        Composition("Ogre Normal Composition", "C"),
        Composition("Fox Worst Composition", "D")
    )

    //Lista de tiers
    val tiers = listOf("S", "A", "B", "C", "D")

    //Structura de la screen
    Scaffold(
        topBar = {
            Surface(
                color = primaryLight
            ) {
                TopAppBar(
                    title = {
                        Text(
                            "Comps List",
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
        //Añadimos un column para que este centrado
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(paddingMedium)
        ) {
            //Titulo de la pantalla
            Text(
                text = "MegaBonk Comps Tier List",
                fontSize = textSizeExtraLarge,
                fontWeight = FontWeight.Bold,
                color = onBackgroundLight,
                modifier = Modifier.padding(bottom = paddingMedium)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Cuadro de texto de busqueda
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = {
                        Text(
                            "Buscar",
                            color = onSurfaceVariantLight
                        )
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryLight,
                        unfocusedBorderColor = outlineLight,
                        focusedTextColor = onSurfaceLight,
                        unfocusedTextColor = onSurfaceLight
                    )
                )
                Spacer(modifier = Modifier.width(spacingMedium))
                //Texto del campo de busqueda
                Text(
                    text = "Nombre de la composicion",
                    fontSize = textSizeSmall,
                    color = onSurfaceVariantLight
                )
            }

            //Lista de tiers
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacingMedium)
            ) {
                items(tiers) { tier ->
                    val isExpanded = expandedTiers.contains(tier)
                    val compsInTier = compositions.filter { it.tier == tier }

                    //Fila de tiers
                    TierRow(
                        tier = tier,
                        compositions = compsInTier,
                        isExpanded = isExpanded,
                        onToggle = {
                            expandedTiers = if (isExpanded) {
                                expandedTiers - tier
                            } else {
                                expandedTiers + tier
                            }
                        },
                        navController = navController,
                        paddingMedium = paddingMedium,
                        spacingMedium = spacingMedium,
                        imageSizeSmall = imageSizeSmall,
                        borderWidth = borderWidth,
                        textSizeMedium = textSizeMedium,
                        textSizeHuge = textSizeHuge,
                        surfaceContainerLight = surfaceContainerLight,
                        surfaceContainerHighLight = surfaceContainerHighLight,
                        onSurfaceLight = onSurfaceLight,
                        outlineLight = outlineLight
                    )
                }
            }
        }
    }
}

@Composable
//Funcion para la fila de tiers
fun TierRow(
    tier: String,
    compositions: List<Composition>,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    navController: NavController,
    paddingMedium: androidx.compose.ui.unit.Dp,
    spacingMedium: androidx.compose.ui.unit.Dp,
    imageSizeSmall: androidx.compose.ui.unit.Dp,
    borderWidth: androidx.compose.ui.unit.Dp,
    textSizeMedium: androidx.compose.ui.unit.TextUnit,
    textSizeHuge: androidx.compose.ui.unit.TextUnit,
    surfaceContainerLight: Color,
    surfaceContainerHighLight: Color,
    onSurfaceLight: Color,
    outlineLight: Color
) {
    //Columna para que ocupe todo el ancho
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(surfaceContainerLight)
            .padding(paddingMedium)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            //Letra del tier
            Text(
                text = tier,
                fontSize = textSizeHuge,
                fontWeight = FontWeight.Bold,
                color = onSurfaceLight,
                modifier = Modifier.padding(end = paddingMedium)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        val firstComp = compositions.firstOrNull()
                        if (firstComp != null) {
                            navController.navigate(
                                Screen.CompositionDetail.createRoute(firstComp.name, firstComp.tier)
                            )
                        }
                    }
            ) {
                //PlaceHolder de la imagen de la composicion
                Box(
                    modifier = Modifier
                        .size(imageSizeSmall)
                        .background(surfaceContainerHighLight)
                        .border(borderWidth, outlineLight)
                )

                Spacer(modifier = Modifier.width(paddingMedium))

                //Nombre de la composición
                Text(
                    text = compositions.firstOrNull()?.name ?: "Nombre de la composicion",
                    fontSize = textSizeMedium,
                    color = onSurfaceLight
                )
            }

            Spacer(modifier = Modifier.width(spacingMedium))

            //Icono para hacer mas grande o mas pequeño el tier
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Colapsar" else "Expandir",
                modifier = Modifier.clickable { onToggle() },
                tint = onSurfaceLight
            )
        }

        //Comprobamos si se expande y si es mayor a 1 si es correcto muestra las demas composiciones
        if (isExpanded && compositions.size > 1) {
            Spacer(modifier = Modifier.height(spacingMedium))
            compositions.drop(1).forEach { comp ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 48.dp, top = spacingMedium)
                        .clickable {
                            navController.navigate(
                                Screen.CompositionDetail.createRoute(comp.name, comp.tier)
                            )
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .size(imageSizeSmall)
                            .background(surfaceContainerHighLight)
                            .border(borderWidth, outlineLight)
                    )
                    Spacer(modifier = Modifier.width(paddingMedium))
                    //Nombre de la composicion
                    Text(
                        text = comp.name,
                        fontSize = textSizeMedium,
                        color = onSurfaceLight
                    )
                }
            }
        }
    }
}