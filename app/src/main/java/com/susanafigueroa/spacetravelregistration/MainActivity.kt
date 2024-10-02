package com.susanafigueroa.spacetravelregistration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var surnameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var destinationInputName by remember { mutableStateOf(-1) }
    var destinationInputImage by remember { mutableStateOf(-1) }

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
                            },
                            keyboardOptionsNumeric = false
                        )

                        EditValue(
                            valueInput = surnameInput,
                            onValueChange = {
                                    newSurnameInput -> surnameInput = newSurnameInput
                            },
                            keyboardOptionsNumeric = false
                        )
                    }

                    R.string.age_step2 -> {

                        TextStep(
                            textStepId = R.string.age_text
                        )

                        EditValue(
                            valueInput = ageInput,
                            onValueChange = {
                                    newAgeInput -> ageInput = newAgeInput
                            },
                            keyboardOptionsNumeric = true
                        )
                    }

                    R.string.destination_step3 -> {

                        TextStep(
                            textStepId = R.string.destination_text
                        )

                        ShowSelectedDestination(
                            destinationInputName = destinationInputName
                        )

                        ShowDestinations(
                            destinationInputName = {
                                    newDestinationInputName -> destinationInputName = newDestinationInputName
                            },
                            destinationInputImage = {
                                    newDestinationInputImage -> destinationInputImage = newDestinationInputImage
                            }
                        )
                    }

                    R.string.summary_step4 -> {

                        TextStep(
                            textStepId = R.string.summary_text
                        )

                        ShowSummary(
                            nameInput = nameInput,
                            surnameInput = surnameInput,
                            ageInput = ageInput,
                            destinationInputName = destinationInputName,
                            destinationInputImage = destinationInputImage
                        )

                        Button(onClick = {
                            currentStep = R.string.name_step1
                            CleanData(
                                nameInput = { nameInput = it },
                                surnameInput = { surnameInput = it },
                                ageInput = { ageInput = it },
                                destinationInputName = { destinationInputName = it },
                                destinationInputImage = { destinationInputImage = it }
                            )
                        }) {
                            Text (stringResource(R.string.restart))
                        }
                    }
                }
                val correctData = ValidateData(
                    currentStep = currentStep,
                    nameInput = nameInput,
                    surnameInput = surnameInput,
                    ageInput = ageInput,
                    destinationInputName = destinationInputName
                )

                NextStepButton(
                    currentStep = currentStep,
                    nextStep = {
                            nextStep -> currentStep = nextStep
                    },
                    correctData = correctData
                )
            }
        }
    }
}

@Composable
private fun ValidateData(
    currentStep: Int,
    nameInput: String,
    surnameInput: String,
    ageInput: String,
    destinationInputName: Int
): Boolean {

    var correctData: Boolean = false

    when (currentStep) {
        R.string.name_step1 -> {
            if (!nameInput.equals("") && !surnameInput.equals("")) {
                correctData = true
            }
        }

        R.string.age_step2 -> {
            if (!ageInput.equals("")) {
                val age = ageInput.toIntOrNull()
                if (age != null && age >= 0 && age < 130) {
                    correctData = true
                }
            }
        }

        R.string.destination_step3 -> {
            if (destinationInputName != -1) {
                correctData = true
            }
        }
    }
    return correctData
}

@Composable
private fun NextStepButton(
    currentStep: Int,
    nextStep: (Int) -> Unit,
    correctData: Boolean
) {

    Button(
        onClick = {
            if (correctData) {
                when (currentStep) {
                    R.string.name_step1 -> {
                        nextStep(R.string.age_step2)
                    }

                    R.string.age_step2 -> {
                        nextStep(R.string.destination_step3)
                    }

                    R.string.destination_step3 -> {
                        nextStep(R.string.summary_step4)
                    }
                }
            }
        },
        enabled = correctData
    ) {
        Text(
            text = stringResource(R.string.next_button)
        )
    }
}

private fun CleanData(
    nameInput: (String) -> Unit,
    surnameInput: (String) -> Unit,
    ageInput: (String) -> Unit,
    destinationInputName: (Int) -> Unit,
    destinationInputImage: (Int) -> Unit
) {
    nameInput("")
    surnameInput("")
    ageInput("")
    destinationInputName(-1)
    destinationInputImage(-1)
}

@Composable
private fun EditValue(
    valueInput: String,
    onValueChange: (String) -> Unit,
    keyboardOptionsNumeric: Boolean
){
    TextField(
        value = valueInput,
        onValueChange = onValueChange,
        keyboardOptions = if (keyboardOptionsNumeric) {
            KeyboardOptions(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions.Default
        }
    )

    Spacer(
        modifier = Modifier.height(16.dp)
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

@Composable
private fun ShowSelectedDestination(
    destinationInputName: Int
) {
    if (destinationInputName != -1) {
        Column(
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.White))
                .padding(10.dp),
        ) {
            if (destinationInputName != -1) {
                Text(
                    text = stringResource(R.string.destination_selected) + stringResource(destinationInputName),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,

                    )
            }
        }
    }
}

@Composable
private fun ShowDestinations(
    destinationInputName: (Int) -> Unit,
    destinationInputImage: (Int) -> Unit
) {
    Column(
    ){
        RowPlanets(
            namePlanetId1 = R.string.moon,
            nameImagePlanetId1 = R.drawable.moon,
            namePlanetId2 = R.string.mercury,
            nameImagePlanetId2 = R.drawable.mercury,
            destinationInputName = destinationInputName,
            destinationInputImage = destinationInputImage
        )

        RowPlanets(
            namePlanetId1 = R.string.venus,
            nameImagePlanetId1 = R.drawable.venus,
            namePlanetId2 = R.string.earth,
            nameImagePlanetId2 = R.drawable.earth,
            destinationInputName = destinationInputName,
            destinationInputImage = destinationInputImage
        )

        RowPlanets(
            namePlanetId1 = R.string.mars,
            nameImagePlanetId1 = R.drawable.mars,
            namePlanetId2 = R.string.jupiter,
            nameImagePlanetId2 = R.drawable.jupiter,
            destinationInputName = destinationInputName,
            destinationInputImage = destinationInputImage
        )

        RowPlanets(
            namePlanetId1 = R.string.saturn,
            nameImagePlanetId1 = R.drawable.saturn,
            namePlanetId2 = R.string.uranus,
            nameImagePlanetId2 = R.drawable.uranus,
            destinationInputName = destinationInputName,
            destinationInputImage = destinationInputImage
        )

        RowPlanets(
            namePlanetId1 = R.string.neptune,
            nameImagePlanetId1 = R.drawable.neptune,
            namePlanetId2 = R.string.pluto,
            nameImagePlanetId2 = R.drawable.pluto,
            destinationInputName = destinationInputName,
            destinationInputImage = destinationInputImage
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
    nameImagePlanetId2: Int,
    destinationInputName: (Int) -> Unit,
    destinationInputImage: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    destinationInputName(namePlanetId1)
                    destinationInputImage(nameImagePlanetId1)
                }
        )
        {
            Planet(
                namePlanetId = namePlanetId1,
                nameImagePlanetId = nameImagePlanetId1
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    destinationInputName(namePlanetId2)
                    destinationInputImage(nameImagePlanetId2)
                }
        ) {
            Planet(
                namePlanetId = namePlanetId2,
                nameImagePlanetId = nameImagePlanetId2,
            )
        }
    }
}

@Composable
private fun Planet(
    namePlanetId: Int,
    nameImagePlanetId: Int,
) {
    Text(
        text = stringResource(namePlanetId),
        color = Color(0xFFFFFAA7)
    )

    val imagePlanet = nameImagePlanetId
    Image(
        painter = painterResource(imagePlanet),
        contentDescription = stringResource(namePlanetId),
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .height(50.dp)
            .width(50.dp)
    )
}

@Composable
private fun ShowSummary(
    nameInput: String,
    surnameInput: String,
    ageInput: String,
    destinationInputImage: Int,
    destinationInputName: Int
) {
    Column(
        modifier = Modifier
            .padding(vertical = 40.dp)
            .border(BorderStroke(2.dp, Color.White))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.name, nameInput),
            fontSize = 20.sp,
            color = Color.White
        )

        Text(
            text = stringResource(R.string.surname, surnameInput),
            fontSize = 20.sp,
            color = Color.White
        )

        Text(
            text = stringResource(R.string.age, ageInput),
            fontSize = 20.sp,
            color= Color.White
        )

        if (destinationInputName != -1 && destinationInputImage != -1) {

            Text (
                text = stringResource(R.string.destination) + stringResource(destinationInputName),
                fontSize = 20.sp,
                color = Color.White
            )

            Image(
                painter = painterResource(id = destinationInputImage),
                contentDescription = stringResource(R.string.destination_image),
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )
        }
    }
}

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