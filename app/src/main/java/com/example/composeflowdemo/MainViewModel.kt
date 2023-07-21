package com.example.composeflowdemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val randomNumberRepository: RandomNumberRepository
) : ViewModel() {

    private val _number = MutableStateFlow(0)
    val number: StateFlow<Int> = _number.asStateFlow()

    fun collectRandomNumber() {
        viewModelScope.launch {
            randomNumberRepository.getRandomNumber().collect { number ->
                Log.e("MainViewModel", "number = $number")
                _number.value = number
            }
        }
    }
}