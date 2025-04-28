package com.example.megasenacompose

import android.R.attr.top
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.megasenacompose.ui.theme.GreenMain
import com.example.megasenacompose.ui.theme.MegaSenaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MegaSenaComposeTheme {
                Surface(

                ) {
                    MegaSenaApp()
                }
            }
        }
    }
}

@Composable
fun EditNumberField(modifier: Modifier = Modifier){
    OutlinedTextField(
        value = "",
        onValueChange = {},
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
        EditNumberField()
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Resultado",
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF))
        ){
            Text(
                stringResource(R.string.gen_numbers_txt),
                color = GreenMain
                )
        }
    }

}


