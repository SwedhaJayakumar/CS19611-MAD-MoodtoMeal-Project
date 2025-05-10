package com.example.mood2meal

import android.content.Intent
import android.os.Bundle as AndroidBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mood2meal.ui.theme.Mood2mealTheme
import androidx.core.net.toUri // ✅ KTX Extension Import

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: AndroidBundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mood2mealTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Mood2MealScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Mood2MealScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var mood by remember { mutableStateOf("Happy") }
    var foodSuggestion by remember { mutableStateOf("Ice Cream Sundae") }
    var youtubeUrl by remember { mutableStateOf("https://www.youtube.com/watch?v=dQw4w9WgXcQ") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Mood Detected: $mood", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Suggested Food: $foodSuggestion", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Simulated mood detection logic
            mood = "Tired"
            foodSuggestion = "Hot Chocolate"
            youtubeUrl = "https://www.youtube.com/watch?v=hot-chocolate-review"
        }) {
            Text("Detect Mood")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Watch Food Review on YouTube",
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, youtubeUrl.toUri()) // ✅ using toUri()
                context.startActivity(intent)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Mood2MealPreview() {
    Mood2mealTheme {
        Mood2MealScreen()
    }
}
