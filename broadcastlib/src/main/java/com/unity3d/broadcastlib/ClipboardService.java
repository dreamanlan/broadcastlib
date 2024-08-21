package com.unity3d.broadcastlib;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ClipboardService extends Service {

    private final String TAG = ClipboardService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String text = intent.getStringExtra("text");
            if (text != null) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (clipboard != null) {
                    clipboard.setPrimaryClip(ClipData.newPlainText(TAG, text));
                }
            } else {
                String text2 = intent.getStringExtra("cmd");
                if (text2 != null) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard != null) {
                        clipboard.setPrimaryClip(ClipData.newPlainText(TAG, "[cmd]:" + text2));
                    }
                } else {
                    String text3 = intent.getStringExtra("pkgcmd");
                    if (text3 != null) {
                        int ix = text3.indexOf(':');
                        if (ix > 0) {
                            String left = text3.substring(0, ix);
                            String right = text3.substring(ix + 1);
                            if (left != null && right != null) {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                if (clipboard != null) {
                                    clipboard.setPrimaryClip(ClipData.newPlainText(TAG, "[" + left + "]:" + right));
                                }
                            }
                        }
                    }
                }
            }
        }
        stopSelf();
        return START_NOT_STICKY;
    }
}
