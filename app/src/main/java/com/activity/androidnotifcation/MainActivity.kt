package com.activity.androidnotifcation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.activity.androidnotifcation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //define channel id for notification channel
    private val channelId = "com.activity.androidnotifcation.channel1"
    //define notification manager instance
    private var notificationManager:NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get notification instance
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //passing the parameter to the function
        createNotificationChannel(channelId,"DemoChannel","This is a demo")

        binding.btn.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification(){
        val notificationId = 30
        val notification = NotificationCompat.Builder(this@MainActivity,channelId)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId,notification)
    }

    private fun createNotificationChannel(id: String, name:String,channelDescription: String){
       //validation to avoid app crashes for lower android version of android oreo
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            //register notification channel with the system using notification manager instance
            notificationManager?.createNotificationChannel(channel)

        }
    }
}