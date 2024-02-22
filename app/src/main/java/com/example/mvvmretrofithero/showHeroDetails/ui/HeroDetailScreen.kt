package com.example.mvvmretrofithero.showHeroDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mvvmretrofithero.showHeroDetails.data.network.response.HeroDetailResponse

@Composable
fun HeroDetailScreen(heroDetailViewModel: HeroDetailViewModel, idHero: Int) {

    LaunchedEffect(key1 = idHero){
        heroDetailViewModel.getDetailHeroById(idHero)
    }

    val responseHero: HeroDetailResponse? = heroDetailViewModel.heroDetail.value
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NameHero(nameHero = responseHero?.name ?: "-")
        Row {
            PowerStats(responseHero)
            ImageHero(
                imageUrl = responseHero?.image?.url
                    ?: "https://ih1.redbubble.net/image.1893341687.8294/fposter,small,wall_texture,product,750x1000.jpg"
            )
        }
    }
}

@Composable
fun NameHero(nameHero: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        text = nameHero
    )
}

@Composable
fun ImageHero(imageUrl: String) {
    AsyncImage(model = imageUrl, contentDescription = "Image of superhero")
}

@Composable
fun PowerStats(responseHero: HeroDetailResponse?) {
    Column {
        Title()
        if (responseHero?.powerstats?.power == null) {
            Stats("Power", 0f)
        } else {
            Stats(nameStats = "Power", powerStat = responseHero.powerstats.power.toFloat())
        }

    }
}

@Composable
fun Title() {
    Text(text = "PowerStats")
}

@Composable
fun Stats(nameStats: String, powerStat: Float) {
    Row {
        Text(nameStats)
        LinearProgressIndicator(progress = powerStat)
        Text(text = powerStat.toString())
    }
}