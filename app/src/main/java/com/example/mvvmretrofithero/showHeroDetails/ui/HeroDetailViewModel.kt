package com.example.mvvmretrofithero.showHeroDetails.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofithero.showHeroDetails.data.network.response.HeroDetailResponse
import com.example.mvvmretrofithero.showHeroDetails.domain.HeroDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(private val heroDetailUseCase: HeroDetailUseCase) : ViewModel() {

    private val _heroDetail = MutableLiveData<HeroDetailResponse>()
    val heroDetail: LiveData<HeroDetailResponse> = _heroDetail

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getDetailHeroById(idHero: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = async { heroDetailUseCase.getDetailHeroById(idHero) }
            response.await()
            _heroDetail.postValue(response.getCompleted())
        }
    }
}