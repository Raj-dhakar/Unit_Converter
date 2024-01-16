package com.example.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconvertor.ui.theme.UnitConvertorTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember{ mutableStateOf("")}
    var outputValue by remember{ mutableStateOf("")}
    var inputUnit by remember{ mutableStateOf("Meters")}
    var outputUnit by remember{ mutableStateOf("Meters")}
    var iExpanded by remember{ mutableStateOf(false)}
    var oExpanded by remember{ mutableStateOf(false)}
    var iconversionFactor = remember{ mutableStateOf(1.00) }
    var oconversionFactor = remember{ mutableStateOf(1.00) }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 36.sp,
        color = Color.Red
    )
    fun convertUnits(){
        // ?: elvis operator
        val inputValueDouble=inputValue.toDoubleOrNull()?: 0.0
        val result=(inputValueDouble * iconversionFactor.value*100.0/oconversionFactor.value).roundToInt()/100
        outputValue=result.toString()
     }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Here all the UI element wiil be stacked below each other
        Text(text="Unit Converter", Modifier.padding(16.dp),
            style = customTextStyle)
//        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue=it
                convertUnits()
        // here goes what should happen when the value of our outLinedTextField changes
        }, label = {Text(text = "Enter the value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            // Input Box
            Box{
                // Input Button
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded=false}) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Centimeter"
                            iconversionFactor.value=0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Meters"
                            iconversionFactor.value=1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Feet"
                            iconversionFactor.value=0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Milimeter")},
                        onClick = {
                            iExpanded=false
                            inputUnit="Milimeter"
                            iconversionFactor.value=0.001
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output Box
            Box{
                // Output button
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeter")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Centimeter"
                            oconversionFactor.value=0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Meters"
                            oconversionFactor.value=1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Feet"
                            oconversionFactor.value=0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Milimeter")},
                        onClick = {
                            oExpanded=false
                            outputUnit="Milimeter"
                            oconversionFactor.value=0.001
                            convertUnits()
                        })
                }
            }
        }
            // Here all the UI element will be stacked ext to each other

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.headlineMedium)
    }
    }

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}