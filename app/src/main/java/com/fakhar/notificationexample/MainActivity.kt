package com.fakhar.notificationexample

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var notifcationManager : NotificationManagerCompat

    lateinit var editTextTitle : EditText
    lateinit var editTextMessage : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextTitle = et_Title
        editTextMessage = et_Message

        notifcationManager = NotificationManagerCompat.from(this)

        var button1 : Button = btn1
        var button2 : Button = btn2

        button1.setOnClickListener(View.OnClickListener {
                Button1Clicked()
        })

        button2.setOnClickListener(View.OnClickListener {
            Button2Clicked()
        })
    }

     fun Button1Clicked()
    {

        var title : String  = editTextTitle.text.toString()
        var message : String  = editTextMessage.text.toString()

        var activityIntent : Intent  = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        var broadcastIntent : Intent = Intent(this, NotifcationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage" , message)

        var actionIntent : PendingIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        var notifation : Notification =  NotificationCompat.Builder(this , App.CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_one)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setColor(Color.BLUE)
            .addAction(R.mipmap.ic_launcher, "Toast",actionIntent)
            .build()

        notifcationManager.notify(1, notifation )
    }

     fun Button2Clicked()
    {
        var title : String  = editTextTitle.text.toString()
        var message : String  = editTextMessage.text.toString()

        var notifation : Notification =  NotificationCompat.Builder(this , App.CHANNEL_2_ID)
            .setSmallIcon(R.drawable.ic_two)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .build()

        notifcationManager.notify(2, notifation )

    }
}
