package com.susanafigueroa.spacetravelregistration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.susanafigueroa.spacetravelregistration.ui.theme.SpaceTravelRegistrationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpaceTravelRegistrationTheme {
                SpaceTravelRegistrationApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpaceTravelRegistration() {

    var currentStep by remember { mutableStateOf(R.string.name_step1) }

    var nameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf(-1) }
    var destinationInput by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.space_travel_registration),
                        color = Color.LightGray
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0x4BA4F5FF)
                )
            )
        }
    ) { innerPadding ->
        Surface (
            modifier = Modifier
                .fillMaxSize()
        ) {

            BackgroundImage()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when (currentStep) {

                    R.string.name_step1 -> {

                        Text (
                            text = stringResource(R.string.name_text),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        EditValue(
                            valueInput = nameInput,
                            onValueChange = {
                                newNameInput -> nameInput = newNameInput
                            }
                        )
                    }

                    R.string.age_step2 -> {

                        Text (
                            text = stringResource(R.string.age_text),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                    }

                    R.string.destination_step3 -> {

                        Text (
                            text = stringResource(R.string.destination_text),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                    }

                    R.string.summary_step4 -> {

                        Text (
                            text = stringResource(R.string.summary_space_travel),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )
                    }
                }
            }

        }
    }
}

@Composable
private fun EditValue(
    valueInput: String,
    onValueChange: (String) -> Unit
){
    TextField(
        value = valueInput,
        onValueChange = onValueChange
    )
}

@Composable
private fun BackgroundImage() {
    val imageBackground = painterResource(R.drawable.galaxy_night_landscape)

    Image(
        painter = imageBackground,
        contentDescription = "image background",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun SpaceTravelRegistrationApp() {
    SpaceTravelRegistrationTheme {
        SpaceTravelRegistration()
    }
}