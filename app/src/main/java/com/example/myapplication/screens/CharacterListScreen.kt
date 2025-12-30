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

//Esta data class la utilizamos para hacer la plantilla de los characters tiene su nombre, la descripcion del personaje, la arma inicial que tiene el personaje
//y en imageResId es donde ponemos el drawable que es la imagen del personaje
data class Character(
    val name: String,
    val initialWeapon: String,
    val description: String,
    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Esta funcion es la encargada de printear por pantalla la lista de characters
fun CharacterListScreen(navController: NavController) {
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
    val tertiaryLight = colorResource(id = R.color.tertiary_light)

    //Esta variable guarda el texto de la barra de busqueda
    var searchText by remember { mutableStateOf("") }

    //Esto es la lista de characters que se basa en la data class que es la plantilla hecha anteriormente
    val characters = listOf(
        Character(
            name = "Monke",
            initialWeapon = "Banana",
            description = "Climb up walls. Also gain +2 Max HP per level.",
            imageResId = R.drawable.monke

        ),
        Character(
            name = "Calcium",
            initialWeapon = "Bone",
            description = "Speed builds up over time, but is halved when you take damage. Also gain damage based on speed.",
            imageResId = R.drawable.calcium
        ),
        Character(
            name = "Sir Oofie",
            initialWeapon = "Sword",
            description = "Gain 1% Armor per level.",
            imageResId = R.drawable.siroofie
        ),
        Character(
            name = "Bandit",
            initialWeapon = "Dexecutioner",
            description = "Gain 1% Attack Speed per level.",
            imageResId = R.drawable.bandit
        ),
        Character(
            name = "Megachad",
            initialWeapon = "Aura",
            description = "Flex to stop damage. Cooldown -0.2s per level. Gain 2.5% Damage when flexing.",
            imageResId = R.drawable.megachad
        ),
        Character(
            name = "Fox",
            initialWeapon = "Firestaff",
            description = "Gain 2% Luck per level.",
            imageResId = R.drawable.fox
        ),
        Character(
            name = "Ogre",
            initialWeapon = "Axe",
            description = "Gain 1.5% Damage per level.",
            imageResId = R.drawable.ogre
        ),
        Character(
            name = "CL4NK",
            initialWeapon = "Rvolver",
            description = "Gain 1% Crit Chance per level.",
            imageResId = R.drawable.cl4nk
        )
    )

    //En este if que se iguala en la veriable filtramos los personajes segun el texto que hemos ingresado en la variable searchText
    val filteredCharacters = if (searchText.isEmpty()) {
        characters
    } else {
        characters.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    //Definimos la estructura que tendra la pantalla del mobil de esta screen
    Scaffold(
        topBar = {
            //La barra superior
            Surface(
                color = primaryLight
            ) {
                TopAppBar(
                    title = {
                        Text(
                            "Character List",
                            color = onPrimaryLight
                        )
                    },
                    navigationIcon = {
                        //Boton de volver atras
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
        //Definimos un column para que este centrado todo igual
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(paddingMedium)
        ) {
            //Título
            Text(
                text = "Character List",
                fontSize = textSizeExtraLarge,
                fontWeight = FontWeight.Bold,
                color = onBackgroundLight,
                modifier = Modifier.padding(bottom = paddingMedium)
            )

            //Barra de búsqueda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingMedium)
                    .background(surfaceContainerHighLight)
                    .border(borderWidth, outlineLight)
                    .padding(spacingSmall)
            ) {
                //Barra de texto para la busqueda de personajes
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

            //Lista de personajes
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(spacingLarge)
            ) {
                items(filteredCharacters) { character ->
                    CharacterCard(
                        character = character,
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
                        outlineLight = outlineLight,
                        tertiaryLight = tertiaryLight
                    )
                }
            }
        }
    }
}

@Composable
//Funcion para dibujar la tarjeta con la informacion del personaje detallada anteriormente
fun CharacterCard(
    character: Character,
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
    outlineLight: Color,
    tertiaryLight: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to character details */ }
            .border(borderWidth, outlineLight),
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerLight
        )
    ) {
        Row(
            modifier = Modifier
                .padding(spacingLarge)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            //Imagen del personaje
            Image(
                painter = painterResource(id = character.imageResId),
                contentDescription = character.name,
                modifier = Modifier
                    .size(imageSizeLarge)
                    .border(borderWidth, outlineLight),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(spacingExtraLarge))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                //Nombre del personaje
                Text(
                    text = character.name,
                    fontSize = textSizeLarge,
                    fontWeight = FontWeight.Bold,
                    color = onSurfaceLight
                )

                Spacer(modifier = Modifier.height(spacingSmall))

                //Texto del arma inicial
                Row {
                    Text(
                        text = "Initial Weapon: ",
                        fontSize = textSizeSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = onSurfaceVariantLight
                    )
                    Text(
                        text = character.initialWeapon,
                        fontSize = textSizeSmall,
                        color = tertiaryLight,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(spacingSmall))

                //Descripción del personaje
                Text(
                    text = character.description,
                    fontSize = textSizeSmall,
                    color = onSurfaceLight,
                    lineHeight = 20.sp
                )
            }
        }
    }
}