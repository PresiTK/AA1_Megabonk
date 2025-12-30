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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

//Definimos la data class como base de los items esta data class contiene el nombre del item, la descripcion y la imagen del item
data class Item(
    val name: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(navController: NavController) {
    // Obtener el contexto para acceder a los recursos
    val context = LocalContext.current
    val resources = context.resources

    // Cargar las dimensiones desde el XML
    val paddingMedium = resources.getDimension(R.dimen.padding_medium).dp
    val spacingSmall = resources.getDimension(R.dimen.spacing_small).dp
    val spacingLarge = resources.getDimension(R.dimen.spacing_large).dp
    val spacingExtraLarge = resources.getDimension(R.dimen.spacing_extra_large).dp
    val imageSizeLarge = resources.getDimension(R.dimen.image_size_large).dp
    val borderWidth = resources.getDimension(R.dimen.border_width).dp
    val textSizeSmall = resources.getDimension(R.dimen.text_size_small).sp
    val textSizeLarge = resources.getDimension(R.dimen.text_size_large).sp
    val textSizeExtraLarge = resources.getDimension(R.dimen.text_size_extra_large).sp

    // Cargar los colores desde el XML
    val primaryLight = colorResource(id = R.color.primary_light)
    val onPrimaryLight = colorResource(id = R.color.on_primary_light)
    val backgroundLight = colorResource(id = R.color.background_light)
    val onBackgroundLight = colorResource(id = R.color.on_background_light)
    val surfaceContainerLight = colorResource(id = R.color.surface_container_light)
    val surfaceContainerHighLight = colorResource(id = R.color.surface_container_high_light)
    val onSurfaceLight = colorResource(id = R.color.on_surface_light)
    val onSurfaceVariantLight = colorResource(id = R.color.on_surface_variant_light)
    val outlineLight = colorResource(id = R.color.outline_light)

    //Variable para guardar el texto de la barra de busqueda
    var searchText by remember { mutableStateOf("") }

    //Lista para la defimicion de los items utilizando la data class definida anteriormente
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

    //Filtra los items segun el texto guardado en la variable searchText si esta vacia se pueden ver todos
    val filteredItems = if (searchText.isEmpty()) {
        items
    } else {
        items.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    //Definimos la estructura de la pantalla de esta screen
    Scaffold(
        topBar = {
            Surface(
                color = primaryLight
            ) {
                TopAppBar(
                    title = {
                        Text(
                            "Item List",
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
    ) { paddingValues ->
        //Este column centra todo el texto en la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(paddingMedium)
        ) {
            //Titulo de la pantalla
            Text(
                text = "Item List",
                fontSize = textSizeExtraLarge,
                fontWeight = FontWeight.Bold,
                color = onBackgroundLight,
                modifier = Modifier.padding(bottom = paddingMedium)
            )
            //Este es el contenedor de la barra de busqueda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium)
                    .background(surfaceContainerHighLight)
                    .border(borderWidth, outlineLight)
                    .padding(spacingSmall)
            ) {
                //Definimos el contenedor donde se escribe el texto
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = {
                        Text(
                            "Buscar",
                            color = onSurfaceVariantLight
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryLight,
                        unfocusedBorderColor = outlineLight,
                        focusedTextColor = onSurfaceLight,
                        unfocusedTextColor = onSurfaceLight
                    )
                )
            }
            //Lista de items en vertical para su visualizacion en pantalla
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacingLarge)
            ) {
                items(filteredItems) { item ->
                    ItemCard(
                        item = item,
                        imageSizeLarge = imageSizeLarge,
                        borderWidth = borderWidth,
                        spacingSmall = spacingSmall,
                        spacingLarge = spacingLarge,
                        spacingExtraLarge = spacingExtraLarge,
                        textSizeSmall = textSizeSmall,
                        textSizeLarge = textSizeLarge,
                        surfaceContainerLight = surfaceContainerLight,
                        onSurfaceLight = onSurfaceLight,
                        onSurfaceVariantLight = onSurfaceVariantLight,
                        outlineLight = outlineLight
                    )
                }
            }
        }
    }
}

@Composable
//Funcion utilizada para dibujar las tarjetas de cada item
fun ItemCard(
    item: Item,
    imageSizeLarge: androidx.compose.ui.unit.Dp,
    borderWidth: androidx.compose.ui.unit.Dp,
    spacingSmall: androidx.compose.ui.unit.Dp,
    spacingLarge: androidx.compose.ui.unit.Dp,
    spacingExtraLarge: androidx.compose.ui.unit.Dp,
    textSizeSmall: androidx.compose.ui.unit.TextUnit,
    textSizeLarge: androidx.compose.ui.unit.TextUnit,
    surfaceContainerLight: Color,
    onSurfaceLight: Color,
    onSurfaceVariantLight: Color,
    outlineLight: Color
) {
    //Dibujamos las cards en pantalla
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to item details */ }
            .border(borderWidth, outlineLight),
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLight
        )
    ) {
        //Lo ponemos en una fila para que este alineado la imagen y el texto
        Row(
            modifier = Modifier
                .padding(spacingLarge)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Imagen del item
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.name,
                modifier = Modifier
                    .size(imageSizeLarge)
                    .border(borderWidth, outlineLight),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(spacingExtraLarge))
            //Hacemos una columna para poder poner las cosas debajo y no quede todo en la misma linea
            Column(
                modifier = Modifier.weight(1f)
            ) {
                //Nombre del item
                Text(
                    text = item.name,
                    fontSize = textSizeLarge,
                    fontWeight = FontWeight.Bold,
                    color = onSurfaceLight
                )

                Spacer(modifier = Modifier.height(spacingSmall))
                //Descripcion del item
                Text(
                    text = item.description,
                    fontSize = textSizeSmall,
                    color = onSurfaceVariantLight
                )
            }
        }
    }

}