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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
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

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(onClick = { currentStep = R.string.age_step2 }) {
                            Text (stringResource(R.string.next_button))
                        }
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

                        Text (
                            text = stringResource(R.string.destination_text),
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        ShowDestinations()

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Button(onClick = { currentStep = R.string.summary_step4 }) {
                            Text (stringResource(R.string.next_button))
                        }
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

                        // ShowSummary()

                        Spacer(
                            modifier = Modifier.height(16.dp)
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

@Composable
private fun ShowDestinations() {
    Column(
    ){
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(
                    text = stringResource(R.string.image_moon),
                    color = Color(0xFFFFFAA7)
                )

                val imageMoon = painterResource(R.drawable.moon)
                Image(
                    painter = imageMoon,
                    contentDescription = stringResource(R.string.image_moon),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.image_mercury),
                    color = Color(0xFFFFFAA7)
                )

                val imageMercury = painterResource(R.drawable.mercury)
                Image(
                    painter = imageMercury,
                    contentDescription = stringResource(R.string.image_mercury),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.image_venus),
                    color = Color(0xFFFFFAA7)
                )

                val imageVenus = painterResource(R.drawable.venus)
                Image(
                    painter = imageVenus,
                    contentDescription = stringResource(R.string.image_venus),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.image_earth),
                    color = Color(0xFFFFFAA7)
                )

                val imageEarth = painterResource(R.drawable.earth)
                Image(
                    painter = imageEarth,
                    contentDescription = stringResource(R.string.image_earth),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.image_mars),
                    color = Color(0xFFFFFAA7)
                )

                val imageMars = painterResource(R.drawable.mars)
                Image(
                    painter = imageMars,
                    contentDescription = stringResource(R.string.image_mars),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = stringResource(R.string.image_jupiter),
                    color = Color(0xFFFFFAA7)
                )

                val imageJupiter = painterResource(R.drawable.jupiter)
                Image(
                    painter = imageJupiter,
                    contentDescription = stringResource(R.string.image_jupiter),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = stringResource(R.string.image_saturn),
                    color = Color(0xFFFFFAA7)
                )

                val imageSaturn = painterResource(R.drawable.saturn)
                Image(
                    painter = imageSaturn,
                    contentDescription = stringResource(R.string.image_saturn),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.image_uranus),
                    color = Color(0xFFFFFAA7)
                )

                val imageUranus = painterResource(R.drawable.uranus)
                Image(
                    painter = imageUranus,
                    contentDescription = stringResource(R.string.image_uranus),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.image_neptune),
                    color = Color(0xFFFFFAA7)
                )

                val imageNeptune = painterResource(R.drawable.neptune)
                Image(
                    painter = imageNeptune,
                    contentDescription = stringResource(R.string.image_neptune),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(R.string.image_pluto),
                    color = Color(0xFFFFFAA7)
                )

                val imagePluto = painterResource(R.drawable.pluto)
                Image(
                    painter = imagePluto,
                    contentDescription = stringResource(R.string.image_pluto),
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .height(50.dp)
                        .width(50.dp)
                )
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