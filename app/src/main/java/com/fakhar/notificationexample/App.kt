package com.fakhar.notificationexample

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build



class App : Application() {

    companion object{
        const val CHANNEL_1_ID :String =  "channel1"
        const val CHANNEL_2_ID :String =  "channel2"
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = CHANNEL_1_ID
            val descriptionText = "This is "
            val channel1 = NotificationChannel(CHANNEL_1_ID, name, NotificationManager.IMPORTANCE_HIGH).apply {
                description = descriptionText + CHANNEL_1_ID
            }

            val channel2 = NotificationChannel(CHANNEL_2_ID, name, NotificationManager.IMPORTANCE_LOW).apply {
                description = descriptionText + CHANNEL_2_ID
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel1)
            notificationManager.createNotificationChannel(channel2)
        }
    }
}