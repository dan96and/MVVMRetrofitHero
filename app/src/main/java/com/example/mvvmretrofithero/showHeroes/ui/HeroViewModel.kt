package com.example.mvvmretrofithero.showHeroes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofithero.showHeroes.data.network.response.HeroResponse
import com.example.mvvmretrofithero.showHeroes.data.network.response.SuperHero
import com.example.mvvmretrofithero.showHeroes.domain.HeroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val heroUseCase: HeroUseCase) : ViewModel() {

    private val _valueSearch = MutableLiveData<String>()
    var valueSearch: LiveData<String> = _valueSearch

    private val _heroList = MutableLiveData<HeroResponse>()
    val heroList: LiveData<HeroResponse> = _heroList

    private val _enableSpinner = MutableLiveData<Boolean>()
    val enableSpinner: LiveData<Boolean> = _enableSpinner

    private val _enableNoResultsFound = MutableLiveData<Boolean>()
    val enableNoresultsFound: MutableLiveData<Boolean> = _enableNoResultsFound

    fun onValueChange(valueSearch: String) {
        _valueSearch.value = valueSearch
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getListHeroByName(nameHero: String) {
        viewModelScope.launch {
            _enableSpinner.value = true
            _enableNoResultsFound.value = false
            val response = async { heroUseCase.getListHeroByName(nameHero) }
            response.await()
            checkNoResultsFound(response.getCompleted(), nameHero)
            _enableSpinner.value = false
        }
    }

    private fun checkNoResultsFound(response: HeroResponse, nameHero: String) {
        if (response.response == "error") {
            _enableNoResultsFound.value = true
        } else {
            response.results.sortedWith(compareBy<SuperHero> {
                !it.name.startsWith(nameHero, ignoreCase = true)
            }.thenBy {
                it.name
            })

//            _heroList.postValue(response2)
        }
    }
}