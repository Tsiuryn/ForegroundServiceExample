package com.example.foregroundserviceexample

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.foregroundserviceexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val filter = IntentFilter()
        filter.addAction("service.to.activity.transfer")
        val updateUIReciver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                binding.layoutId.setBackgroundColor(Color.BLACK)
            }
        }
        registerReceiver(updateUIReciver, filter)



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }



        binding.btnStartService.setOnClickListener{
                Intent(applicationContext, RunningService::class.java).also{
                        it.action = RunningService.Actions.START.toString()
                        startService(it)
                }
        }

        binding.btnStopService.setOnClickListener{
            Intent(applicationContext, RunningService::class.java).also{
                it.action = RunningService.Actions.STOP.toString()
                startService(it)
            }
        }

    }

}