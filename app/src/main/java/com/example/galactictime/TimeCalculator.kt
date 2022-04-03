package com.example.galactictime

import java.util.*

class TimeCalculator {

    fun calc(): Double {

        //time from 01/01/1970 to now in millis
        val timeSinceEpoch = Calendar.getInstance().timeInMillis
        //Add the time from 01/01/1860 to 01/01/1970 in milliseconds and convert to seconds
        val totalTime = (timeSinceEpoch + 3471265902000) / 1000

        return totalTime / 1.0878277570776
    }

}