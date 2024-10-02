package com.susanafigueroa.spacetravelregistration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
    var ageInput by remember { mutableStateOf("") }
    var destinationInput by remember { mutableStateOf("") }

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

                        TextStep(
                            textStepId = R.string.name_text
                        )

                        EditValue(
                            valueInput = nameInput,
                            onValueChange = {
                                newNameInput -> nameInput = newNameInput
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(onClick = { currentStep = R.string.age_step2 }) {
                            Text (stringResource(R.string.next_button))
                        }
                    }

                    R.string.age_step2 -> {

                        TextStep(
                            textStepId = R.string.age_text
                        )

                        EditValue(
                            valueInput = ageInput,
                            onValueChange = {
                                    newAgeInput -> ageInput = newAgeInput
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(onClick = { currentStep = R.string.destination_step3 }) {
                            Text (stringResource(R.string.next_button))
                        }

                    }

                    R.string.destination_step3 -> {

                        TextStep(
                            textStepId = R.string.destination_text
                        )

                        ShowDestinations()

                        Button(onClick = { currentStep = R.string.summary_step4 }) {
                            Text (stringResource(R.string.next_button))
                        }
                    }

                    R.string.summary_step4 -> {

                        TextStep(
                            textStepId = R.string.summary_text
                        )

                        ShowSummary(
                            nameInput,
                            ageInput
                        )

                        Button(onClick = { currentStep = R.string.name_step1 }) {
                            Text (stringResource(R.string.next_button))
                        }

                    }
                }
            }

        }
    }
}

// TEXTFIELD
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
private fun TextStep(textStepId: Int) {
    Text (
        text = stringResource(textStepId),
        color = Color.White,
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(
        modifier = Modifier.height(16.dp)
    )
}

// DESTINATIONS
@Composable
private fun ShowDestinations() {
    Column(
    ){

        RowPlanets(
            namePlanetId1 = R.string.moon,
            nameImagePlanetId1 = R.drawable.moon,
            namePlanetId2 = R.string.mercury,
            nameImagePlanetId2 = R.drawable.mercury
        )

        RowPlanets(
            namePlanetId1 = R.string.venus,
            nameImagePlanetId1 = R.drawable.venus,
            namePlanetId2 = R.string.earth,
            nameImagePlanetId2 = R.drawable.earth
        )

        RowPlanets(
            namePlanetId1 = R.string.mars,
            nameImagePlanetId1 = R.drawable.mars,
            namePlanetId2 = R.string.jupiter,
            nameImagePlanetId2 = R.drawable.jupiter
        )

        RowPlanets(
            namePlanetId1 = R.string.saturn,
            nameImagePlanetId1 = R.drawable.saturn,
            namePlanetId2 = R.string.uranus,
            nameImagePlanetId2 = R.drawable.uranus
        )

        RowPlanets(
            namePlanetId1 = R.string.neptune,
            nameImagePlanetId1 = R.drawable.neptune,
            namePlanetId2 = R.string.pluto,
            nameImagePlanetId2 = R.drawable.pluto
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}

@Composable
private fun RowPlanets(
    namePlanetId1: Int,
    nameImagePlanetId1: Int,
    namePlanetId2: Int,
    nameImagePlanetId2: Int
) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Planet(
                namePlanetId = namePlanetId1,
                nameImagePlanetId = nameImagePlanetId1
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Planet(
                namePlanetId = namePlanetId2,
                nameImagePlanetId = nameImagePlanetId2
            )
        }
    }
}

@Composable
private fun Planet(
    namePlanetId: Int,
    nameImagePlanetId: Int
) {
    Text(
        text = stringResource(namePlanetId),
        color = Color(0xFFFFFAA7)
    )

    val imageNeptune = nameImagePlanetId
    Image(
        painter = painterResource(imageNeptune),
        contentDescription = stringResource(namePlanetId),
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .height(50.dp)
            .width(50.dp)
    )
}

//SUMMARY
@Composable
private fun ShowSummary(
    nameInput: String,
    ageInput: String
) {

    Text(
        text = nameInput,
        color = Color.White
    )

    Text(
        text = ageInput,
        color= Color.White
    )

}

// BACKGROUND
@Composable
private fun BackgroundImage() {
    val imageBackground = painterResource(R.drawable.galaxy_night_landscape)

    Image(
        painter = imageBackground,
        contentDescription = stringResource(R.string.image_background),
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