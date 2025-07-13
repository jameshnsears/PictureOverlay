package com.github.jameshnsears.pictureoverlay.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.github.jameshnsears.pictureoverlay.viewmodel.GreetingViewModel

@Composable
fun Greeting() {
    val vm = GreetingViewModel()

    val number = remember { vm.n }

    Button(onClick = {
        vm.increment()
    }) {
        Text("+ = " + number.value)
    }
}
