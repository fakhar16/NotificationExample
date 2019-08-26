package com.fakhar.notificationexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotifcationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        var message : String? = intent?.getStringExtra("toastMessage")
        Toast.makeText(context , message , Toast.LENGTH_LONG).show()
    }
}