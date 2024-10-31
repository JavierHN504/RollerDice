package com.example.rollerdice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rollerdice.ui.theme.RollerDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RollerDiceTheme {
                DiceRollerApp()

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceWithButtonAndImage(modifier:  Modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)){


    var result by remember { mutableIntStateOf(1) }
    var userValue by remember { mutableIntStateOf(0) }
    var textoLabel by remember { mutableStateOf("Buena Suerte") }
    
    val imageResource = when (result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    TopAppBar(title = { stringResource(R.string.app_name)},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primaryContainer

        ),)
    

    Column (modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){

        Image(painter = painterResource(imageResource), contentDescription = "1" )


        TextField(
            userValue.toString(),
            onValueChange = { userValue = it.toInt()},
            label = {
                Text(text = stringResource(R.string.textAreaUser))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            result = (1..6).random()
            if (result == userValue){
                textoLabel = "Has ganao"
            }else{
                textoLabel = "Intenta de nuevo"
            }

        }) {
            Text(stringResource(R.string.roller))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(textoLabel)


    }



}













