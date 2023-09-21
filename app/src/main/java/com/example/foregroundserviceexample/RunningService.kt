package com.example.foregroundserviceexample

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat


class RunningService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val intentAction = Intent(this, ActionReceiver::class.java)
        intentAction.putExtra("action", "actionName")
        val pIntentlogin =
            PendingIntent.getBroadcast(this, 1, intentAction, PendingIntent.FLAG_MUTABLE);
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("action", "actionName")
        val pIntentActivity =
            PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);

        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle("Run is active")
            .setContentText("Elapsed time: 00:50")
            .addAction(R.drawable.ic_add, "Change background", pIntentlogin)
            .addAction(R.drawable.ic_launcher_background, "Open activity", pIntentActivity)
            .build()

        startForeground(1, notification)
    }

    enum class Actions {
        START, STOP
    }
}