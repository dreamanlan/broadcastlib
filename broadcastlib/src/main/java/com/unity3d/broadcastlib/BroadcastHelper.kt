package com.unity3d.broadcastlib

import android.content.BroadcastReceiver;

class BroadcastHelper(callback: IBroadcastReceiver?) : BroadcastReceiver() {
    override fun onReceive(context: android.content.Context?, intent: android.content.Intent?) {
        m_Callback?.onReceive(context, intent);
    }
    var m_Callback : IBroadcastReceiver? = callback;
}