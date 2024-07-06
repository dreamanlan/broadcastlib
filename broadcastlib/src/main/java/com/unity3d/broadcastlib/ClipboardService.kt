package com.unity3d.broadcastlib

import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder

class ClipboardService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (null != intent) {
            val text = intent.getStringExtra("text")
            if (null != text) {
                var srv = getSystemService(Context.CLIPBOARD_SERVICE)
                if(null != srv) {
                    val clipboard = srv as ClipboardManager
                    clipboard?.setPrimaryClip(ClipData.newPlainText(TAG, text))
                }
            } else {
                val text2 = intent?.getStringExtra("dsl_cmd");
                if (null != text2) {
                    var srv = getSystemService(Context.CLIPBOARD_SERVICE)
                    if (null != srv) {
                        val clipboard = srv as ClipboardManager
                        clipboard?.setPrimaryClip(ClipData.newPlainText(TAG, "[dsl_cmd]:$text2"))
                    }
                }
            }
        }
        stopSelf()
        return START_NOT_STICKY
    }

    private val TAG = ClipboardService::class.java.simpleName
}