package com.example.coroutinetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.coroutinetest.ui.theme.CoroutineTestTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mainViewModel = MainViewModel(RandomNumberRepository(RandomNumberDataSource()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "main branch")
        setContent {
            CoroutineTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(modifier = Modifier.fillMaxSize(), mainViewModel)
                }
            }
        }

        mainViewModel.collectRandomNumber()
        Log.e("MainActivity", "collectRandomNumber")

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                mainViewModel.number.collectLatest { number ->
                    Log.e("MainActivity ", "it = $number")
                    //if you don't want to use compose
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, viewmodel: MainViewModel) {

    val number = viewmodel.number.collectAsState(initial = -1)

    Text(
        text = "My Number : ${number.value}",
        modifier = modifier
    )
}
