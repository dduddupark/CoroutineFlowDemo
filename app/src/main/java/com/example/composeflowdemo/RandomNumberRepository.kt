package com.example.composeflowdemo

import kotlinx.coroutines.flow.filter

class RandomNumberRepository(
    private val randomNumberDataSource: RandomNumberDataSource
) {
    fun getRandomNumber() = randomNumberDataSource.getRandomNumber().filter { it < 9 }
}