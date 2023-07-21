package com.example.composeflowdemo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class RandomNumberDataSource {

    fun getRandomNumber() = flow {
        while (true) {
            emit((1..10).random())
            delay(3000)
        }
    }
}