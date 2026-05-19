package ca.lazanomentsoarabesandratana.reader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.lazanomentsoarabesandratana.reader.screens.ReaderSplashScreen
import ca.lazanomentsoarabesandratana.reader.screens.details.BookDetailsScreen
import ca.lazanomentsoarabesandratana.reader.screens.home.Home
import ca.lazanomentsoarabesandratana.reader.screens.login.ReaderLoginScreen
import ca.lazanomentsoarabesandratana.reader.screens.search.SearchScreen
import ca.lazanomentsoarabesandratana.reader.screens.stats.ReaderStatsScreen
import ca.lazanomentsoarabesandratana.reader.screens.update.BookUpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name){
        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }

        composable (ReaderScreens.LoginScreen.name){
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.CreateAccountScreen.name){

        }
        composable (ReaderScreens.ReaderHomeScreen.name){
            Home(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

        composable(ReaderScreens.DetailScreen.name){
            BookDetailsScreen(navController = navController)
        }

        composable(ReaderScreens.UpdateScreen.name){
            BookUpdateScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name){
            ReaderStatsScreen(navController = navController)
        }
    }
}