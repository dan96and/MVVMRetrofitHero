package com.example.mvvmretrofithero.showHeroDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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

    LaunchedEffect(key1 = true) {
        heroDetailViewModel.getDetailHeroById(idHero)
    }

    val responseHero: HeroDetailResponse? by heroDetailViewModel.heroDetail.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        NameHero(nameHero = responseHero?.name ?: "-")
        Row {
            PowerStats(responseHero, modifier = Modifier.weight(2f), heroDetailViewModel)
            ImageHero(
                modifier = Modifier.weight(1f),
                imageUrl = responseHero?.image?.url
                    ?: "https://ih1.redbubble.net/image.1893341687.8294/fposter,small,wall_texture,product,750x1000.jpg"
            )
        }
        Column {
            Title("Introducción")
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "El personaje de ${responseHero?.name ?: "-"} su ocupación es ser ${responseHero?.work?.occupation ?: "-"} en ${responseHero?.work?.base ?: "-"}."
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Tiene una estatura de ${responseHero?.appearance?.height?.get(1) ?: "-"} y un peso de ${
                    responseHero?.appearance?.weight?.get(1) ?: "-"
                }."
            )

        }
        Spacer(modifier = Modifier.fillMaxWidth().size(16.dp))
        Column {
            Title("Primera aparición")
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "La primera aparición del personaje fue en comic ${responseHero?.biography?.firstAppearance ?: "-"} publicado por ${responseHero?.biography?.publisher ?: "-"}."
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().size(16.dp))
        
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
fun PowerStats(
    responseHero: HeroDetailResponse?,
    modifier: Modifier,
    heroDetailViewModel: HeroDetailViewModel
) {
    Column(
        modifier = modifier
    ) {
        Title(text = "Power stats")
        Stats(
            nameStats = "Intelligence",
            powerStat = heroDetailViewModel.checkValue(responseHero?.powerstats?.intelligence.toString())
        )
        Stats(
            nameStats = "Strength",
            powerStat = heroDetailViewModel.checkValue(responseHero?.powerstats?.strength.toString())
        )
        Stats(
            nameStats = "Speed",
            powerStat = heroDetailViewModel.checkValue(responseHero?.powerstats?.speed.toString())
        )
        Stats(
            nameStats = "Durability",
            powerStat = heroDetailViewModel.checkValue(responseHero?.powerstats?.durability.toString())
        )
        Stats(
            nameStats = "Power",
            powerStat = heroDetailViewModel.checkValue(responseHero?.powerstats?.power.toString())
        )
        Stats(
            nameStats = "Combat",
            powerStat = heroDetailViewModel.checkValue(responseHero?.powerstats?.combat.toString())
        )
    }
}

@Composable
fun Title(text: String) {
    Text(
        modifier = Modifier
            .padding(vertical = 4.dp), text = text,
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