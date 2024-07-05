package com.unity3d.broadcastlib

interface IBroadcastReceiver {
    fun onReceive(context: android.content.Context?, intent: android.content.Intent?)
}