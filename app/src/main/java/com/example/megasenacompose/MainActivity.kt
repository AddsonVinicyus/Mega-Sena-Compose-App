package com.example.megasenacompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.megasenacompose.ui.theme.GreenMain
import com.example.megasenacompose.ui.theme.MegaSenaComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MegaSenaComposeTheme {
                Surface {
                    MegaSenaApp()
                }
            }
        }
    }


    private fun displayError(){
        Toast.makeText(this, "Informe um número entre 6 e 15", Toast.LENGTH_LONG).show()
    }

    private fun numberGenerator(numberInput: String): String{
        if(numberInput.isEmpty()){
            displayError()
            return ""
        }

        val numberQtd = numberInput.toInt()

        if(numberQtd < 6 || numberQtd > 15){
            displayError()
            return ""
        }

        val numbersList = mutableSetOf<Int>()
        val random = java.util.Random()

        while(numbersList.size < numberQtd){
            val number = random.nextInt(60)
            numbersList.add(number + 1)
        }

        val sortedList = numbersList.sorted()

        return sortedList.joinToString(" - ")

    }

    @Composable
    fun EditNumberField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ){
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            label = { Text(
                stringResource(R.string.number_hint),
                color = Color(0xFFFFFFFF)
            ) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = modifier
        )
    }

    @Preview
    @Composable
    fun MegaSenaApp(modifier: Modifier = Modifier){

        val context = LocalContext.current
        var numberInput by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("Resultado") }
        //val number = numberInput.toIntOrNull() ?: 0

        LaunchedEffect(Unit) {
            result = MegaSenaPrefs.getLastResult(context)
        }

        Column(
            modifier = modifier
                .background(GreenMain)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            Image(
                painter = painterResource(R.drawable.mega_sena_logo),
                contentDescription = stringResource(R.string.logo_desc),
                modifier = modifier.padding(top = 70.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            EditNumberField(
                value = numberInput,
                onValueChange = { numberInput = it}
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = result,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFFFFF),
                modifier = modifier.padding(start = 20.dp, end = 20.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    val newResult = numberGenerator(numberInput)
                    result = newResult
                    CoroutineScope(Dispatchers.IO).launch {
                        MegaSenaPrefs.saveLastResult(context, newResult)
                    }
                          },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF))
            ){
                Text(
                    stringResource(R.string.gen_numbers_txt),
                    color = GreenMain
                )
            }
        }
    }
}










