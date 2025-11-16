package com.example.myapplication


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screens.*

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Register : Screen("register")
    object Welcome : Screen("welcome")
    object CompsList : Screen("comps_list")
    object ItemList : Screen("item_list")
    object CharacterList : Screen("character_list")
    object WeaponList : Screen("weapon_list")
    object TomesList : Screen("tomes_list")
    object CompositionDetail : Screen("composition_detail/{name}/{tier}") {
        fun createRoute(name: String, tier: String) = "composition_detail/$name/$tier"
    }
}

@Composable
fun BonkAPIMNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.CompsList.route) {
            CompsListScreen(navController = navController)
        }
        composable(Screen.ItemList.route) {
            ItemListScreen(navController = navController)
        }
        composable(Screen.CharacterList.route) {
            CharacterListScreen(navController = navController)
        }
        composable(Screen.WeaponList.route) {
            WeaponListScreen(navController = navController)
        }
        composable(Screen.TomesList.route) {
            TomesListScreen(navController = navController)
        }
        composable(
            route = Screen.CompositionDetail.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("tier") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val tier = backStackEntry.arguments?.getString("tier") ?: ""
            CompositionDetailScreen(
                navController = navController,
                compositionName = name,
                tier = tier
            )
        }
    }
}
