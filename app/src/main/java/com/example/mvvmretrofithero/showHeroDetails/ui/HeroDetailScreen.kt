package com.example.mvvmretrofithero.showHeroDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mvvmretrofithero.showHeroDetails.data.network.response.HeroDetailResponse

@Composable
fun HeroDetailScreen(heroDetailViewModel: HeroDetailViewModel, idHero: Int) {

    heroDetailViewModel.getDetailHeroById(idHero)

    val responseHero: HeroDetailResponse? = heroDetailViewModel.heroDetail.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        NameHero(nameHero = responseHero?.name ?: "-")
        Row {
            PowerStats(responseHero, modifier = Modifier.weight(2f))
            ImageHero(
                modifier = Modifier.weight(1f),
                imageUrl = responseHero?.image?.url
                    ?: "https://ih1.redbubble.net/image.1893341687.8294/fposter,small,wall_texture,product,750x1000.jpg"
            )
        }
    }
}

@Composable
fun NameHero(nameHero: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        text = nameHero
    )
}

@Composable
fun ImageHero(imageUrl: String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier.clip(RoundedCornerShape(4.dp)),
        model = imageUrl,
        contentDescription = "Image of superhero"
    )
}

@Composable
fun PowerStats(responseHero: HeroDetailResponse?, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Title()
        Stats(
            nameStats = "Intelligence",
            powerStat = checkValue(responseHero?.powerstats?.intelligence.toString())
        )
        Stats(
            nameStats = "Strength",
            powerStat = checkValue(responseHero?.powerstats?.strength.toString())
        )
        Stats(
            nameStats = "Speed",
            powerStat = checkValue(responseHero?.powerstats?.speed.toString())
        )
        Stats(
            nameStats = "Durability",
            powerStat = checkValue(responseHero?.powerstats?.durability.toString())
        )
        Stats(
            nameStats = "Power",
            powerStat = checkValue(responseHero?.powerstats?.power.toString())
        )
        Stats(
            nameStats = "Combat",
            powerStat = checkValue(responseHero?.powerstats?.combat.toString())
        )
    }
}

private fun checkValue(value: String): Float {
    return if (value != "null") {
        // Si intelligenceString no es nulo y no tiene el valor "null", intenta convertirlo a Float
        try {
            value.toFloat()
        } catch (e: NumberFormatException) {
            // Si la conversión falla, maneja el error aquí
            0f // O un valor predeterminado adecuado
        }
    } else {
        // Si intelligenceString es nulo o tiene el valor "null", asigna un valor predeterminado
        0f // O un valor predeterminado adecuado
    }
}

@Composable
fun Title() {
    Text(
        modifier = Modifier
            .padding(vertical = 4.dp), text = "Power stats",
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        fontSize = 16.sp
    )
}

@Composable
fun Stats(nameStats: String, powerStat: Float) {
    Row {
        Text(
            text = nameStats,
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically),
        )
        LinearProgressIndicator(

            progress = powerStat / 100,
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 6.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = powerStat.toString(),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}