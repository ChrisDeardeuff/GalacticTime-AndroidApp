package com.example.galactictime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galactictime.ui.theme.GalacticTimeTheme

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.modifier.modifierLocalConsumer
import java.util.*
import kotlin.concurrent.timer

class MainActivity : ComponentActivity() {

    private var gt by mutableStateOf("")

    lateinit var routine: Timer

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GalacticTimeTheme(){
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    Layout()
                    MainDisplay(gt)
                }
            }
        }

        routine = timer(period = 100) {
            gt = TimeCalculator().calc().toString()
            notif()
        }

    }



    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"


    fun notif(){
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        if(gt.substring(6, 7) == "0" && gt.substring(7, 9) == "00" && gt.substring(9, 11) == "00"){

            val gd = gt.substring(0,1)+gt.substring(2,3)+"/"+gt.substring(3,4)+"/"+gt.substring(4,6)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this,channelId)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Galactic Time")
                    .setContentText(gd)
                    .setContentIntent(pendingIntent)
            } else {

                builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Galactic Time")
                    .setContentText(gd)
                    .setContentIntent(pendingIntent)
            }

            notificationManager.notify(1234,builder.build())
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        routine.cancel();
    }

}

@Composable
fun MainDisplay(gt: String){
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {

        GalacticTimeDisplay(gt)
        GalacticDateDisplay(gt)
    }

}


@ExperimentalMaterial3Api
@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    GalacticTimeTheme {
        Layout()
        MainDisplay("0.000000000000000")
    }
}


