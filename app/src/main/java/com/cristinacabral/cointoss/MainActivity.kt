package com.cristinacabral.cointoss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinTossApp()
        }
    }
}

@Composable
fun CoinTossApp() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoggedIn by remember { mutableStateOf(false) }

    if (!isLoggedIn) {
        // Login screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White) // Color background
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Login", fontSize = 28.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth() //// Makes the composable take the full width of the parent container
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth() //// Makes the composable take the full width of the parent container
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    isLoggedIn = true
                }
            }) {
                Text("Login")
            }
        }
    } else {

        CoinTossGameScreen()
    }
}

@Composable
fun CoinTossGameScreen() {
    var result by remember { mutableStateOf("") } // // Holds the result of coin toss ("Heads" or "Tails")
    var imageRes by remember { mutableStateOf<Int?>(null) } // // Holds the image resource to display after tossing
    var heads by remember { mutableStateOf(0) } // // Track number of times "Heads" is tossed
    var tails by remember { mutableStateOf(0) } // // Track number of times "Tails" is tossed

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0F7FA)) // Background color
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Heads or Tails", fontSize = 30.sp)

        Spacer(modifier = Modifier.height(20.dp))

        if (result.isNotEmpty()) {
            Text(result, fontSize = 30.sp)

            Spacer(modifier = Modifier.height(12.dp))

            imageRes?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = result,
                    modifier = Modifier.size(100.dp) // Size of Image
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp)) // Adds vertical spacing of 20dp in between

        Button(onClick = {
            if (Random.nextBoolean()) {
                result = "Heads"
                imageRes = R.drawable.heads // Image
                heads++
            } else {
                result = "Tails"
                imageRes = R.drawable.tails // Image
                tails++
            }
        }) {
            Text("Toss Coin")
        }

        Spacer(modifier = Modifier.height(12.dp)) // // Adds vertical spacing of 20dp in between

        Button(onClick = {
            result = ""
            imageRes = null
            heads = 0
            tails = 0
        }) {
            Text("Reset")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Heads: $heads", fontSize = 20.sp) // Text for counter and size
        Text("Tails: $tails", fontSize = 20.sp) // Text for counter and size
    }
}