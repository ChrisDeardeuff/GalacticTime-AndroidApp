package com.example.galactictime

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.concurrent.timer

var gt by mutableStateOf("")
/**
 * Implementation of App Widget functionality.
 */
class GalacticTimeWidget : AppWidgetProvider() {


    private val routine = timer(period = 1000) {
        gt = TimeCalculator().calc().toString()
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {

        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        routine.cancel()
        // Enter relevant functionality for when the last widget is disabled
    }


}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val wizlon = gt.substring(6, 7)
    val peemlon = gt.substring(7, 9)
    val widgetText = "${wizlon}.${peemlon}"

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.galactic_time_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}