package com.example.mvvmretrofithero.showHeroes.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mvvmretrofithero.showHeroes.data.network.response.HeroResponse

@Composable
fun HeroListScreen(heroViewModel: HeroViewModel, navController: NavHostController) {

    val enableSpinner: Boolean by heroViewModel.enableSpinner.observeAsState(initial = false)
    val enableNoResultsFound: Boolean by heroViewModel.enableNoresultsFound.observeAsState(initial = false)

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)) {
        SearchField(heroViewModel)
        if (enableNoResultsFound) {
            NoResultsFound()
        } else {
            if (!enableSpinner) {
                ListHeroLazyColumn(heroViewModel, navController)
            } else {
                SpinnerLoading()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(heroViewModel: HeroViewModel) {

    val valueSearchField: String by heroViewModel.valueSearch.observeAsState(initial = "")
    val keyBoardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = valueSearchField,
        maxLines = 1,
        onValueChange = { heroViewModel.onValueChange(it) },
        placeholder = { Text(text = "Introduce el nombre de un superheroe..") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Button search super heroes",
                modifier = Modifier.clickable {
                    heroViewModel.getListHeroByName(valueSearchField)
                    keyBoardController?.hide()

                })
        })
}

@Composable
fun ListHeroLazyColumn(heroViewModel: HeroViewModel, navController: NavHostController) {

    val listHero: HeroResponse? by heroViewModel.heroList.observeAsState()

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(
            listHero?.results ?: listOf()
        ) {
            CardHero(
                idHero = it.id,
                urlImage = it.image.url, nameHero = it.name, navController = navController
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardHero(idHero: Int, urlImage: String, nameHero: String, navController: NavHostController) {
    Card(
        onClick = { navController.navigate("detailHeroScreen/$idHero") },
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color.Black)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillBounds,
                model = urlImage,
                contentDescription = "Image of super heros"
            )
            Text(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally),
                text = nameHero,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SpinnerLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun NoResultsFound() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .padding(4.dp),
            imageVector = Icons.Default.Search,
            contentDescription = "Search icon"
        )
        Text(
            fontSize = 15.sp,
            text = "No se han encontrado resultados",
            fontWeight = FontWeight.Bold
        )
    }
}