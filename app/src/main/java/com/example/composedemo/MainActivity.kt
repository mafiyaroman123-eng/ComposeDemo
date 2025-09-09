package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun DemoText(message: String, fontSize: Float) {
    Text(
        text = message,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun DemoSlider(
    sliderPosition: Float,
    onPositionChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Slider(
        modifier = modifier.padding(10.dp),
        valueRange = 20f..38f,
        value = sliderPosition,
        onValueChange = onPositionChange
    )
}


@Composable
fun DemoScreen(modifier: Modifier = Modifier) {
    var sliderPosition by remember { mutableFloatStateOf(20f) }
    val handlePositionChange: (Float) -> Unit = { position -> sliderPosition = position }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        DemoText(message = "Welcome to Compose", fontSize = sliderPosition)
        Spacer(modifier = Modifier.height(150.dp))
        DemoSlider(
            sliderPosition = sliderPosition,
            onPositionChange = handlePositionChange
        )
        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = "${sliderPosition.toInt()}sp"
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DemoTextPreview() {
    ComposeDemoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DemoScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}
