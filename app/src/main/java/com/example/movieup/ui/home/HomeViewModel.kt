package com.example.movieup.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieup.MyApp
import com.example.movieup.network.models.ResponseFilms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun getFilms(action: (ResponseFilms) -> Unit) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = MyApp.instance.appComponent.film().getAllFilms()

            viewModelScope.launch {
                response.body()?.let {
                    action(it)
                }
            }
        }
    }

    fun getOffsetFilms(offset: Int, responseCallBack: (ResponseFilms?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                MyApp.instance.appComponent.film().getOffsetFilms(offset = offset)
            viewModelScope.launch {
                responseCallBack(response.body())
            }
        }
    }
}