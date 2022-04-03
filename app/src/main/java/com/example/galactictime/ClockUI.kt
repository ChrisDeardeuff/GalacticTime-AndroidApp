package com.example.galactictime

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galactictime.ui.theme.GalacticTimeTheme
import org.intellij.lang.annotations.JdkConstants


@ExperimentalMaterial3Api
@Composable
fun Layout(){
    Scaffold (
        bottomBar = {
            BottomAppBar() {
                NavBarBottom()
            }
        }
    ){

    }
}

@Composable
fun NavBarBottom(){
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(
            onClick = { /*TODO*/ },
            Modifier.padding(5.dp,0.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.Schedule,"Clock")
                Text("CLOCK")
            }
        }
        Button(
            onClick = { /*TODO*/ },
            Modifier.padding(5.dp,0.dp)
            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.Alarm,"Alarm")
                Text("ALARM")
            }
        }
        Button(
            onClick = { /*TODO*/ },
            Modifier.padding(5.dp,0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.SyncAlt,"Converter")
                Text("CONVERT")
            }
        }
        Button(
            onClick = { /*TODO*/ },
            Modifier.padding(5.dp,0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Filled.Timer,"Timer")
                Text("TIMER")
            }
        }
    }
}

@Composable
fun GalacticDateDisplay(gt: String){
    val gd = gt.substring(0,1)+gt.substring(2,3)+"/"+gt.substring(3,4)+"/"+gt.substring(4,6)
    Row (
        Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.Center,
    ){
        Text(
            text = gd,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 50.sp
        )
    }
}

@Composable
fun GalacticTimeDisplay(gt: String) {

    val wizlon = gt.substring(6, 7)
    val peemlon = gt.substring(7, 9)

    Row (
        Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.Center){
        Text(
            text = "$wizlon.$peemlon",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 100.sp
        )
        Column{
            Spacer(modifier = Modifier.height(55.dp))
            Text(
                text = gt.substring(9, 11),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 50.sp
            )
        }
    }
}



@ExperimentalMaterial3Api
@Preview(showBackground = false)
@Composable
fun BottomPreview() {
    GalacticTimeTheme {
        Layout()
    }
}