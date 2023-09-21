package com.example.foregroundserviceexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ActionReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        val extra = p1?.getStringExtra("action");
        if(extra != null){
            val local = Intent()
            local.action = "service.to.activity.transfer"
            local.putExtra("number", 22) //example
            context?.sendBroadcast(local)

        }
    }
}