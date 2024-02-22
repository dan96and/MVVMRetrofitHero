package com.example.mvvmretrofithero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvmretrofithero.showHeroDetails.ui.HeroDetailScreen
import com.example.mvvmretrofithero.showHeroDetails.ui.HeroDetailViewModel
import com.example.mvvmretrofithero.showHeroes.ui.HeroScreen
import com.example.mvvmretrofithero.showHeroes.ui.HeroViewModel
import com.example.mvvmretrofithero.ui.theme.MVVMRetrofitHeroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val heroViewModel: HeroViewModel by viewModels()
    private val heroDetailViewModel: HeroDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMRetrofitHeroTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "listHeroScreen") {
                        composable("listHeroScreen") { HeroScreen(heroViewModel, navController) }
                        composable(
                            "detailHeroScreen/{idHero}",
                            arguments = listOf(navArgument("idHero") { type = NavType.IntType })
                        ) {
                            val idHero = it.arguments!!.getInt("idHero")
                            HeroDetailScreen(heroDetailViewModel, idHero)
                        }
                    }
                }
            }
        }
    }
}