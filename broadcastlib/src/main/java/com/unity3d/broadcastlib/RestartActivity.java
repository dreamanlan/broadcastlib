package com.unity3d.broadcastlib;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.app.Activity;

public class RestartActivity extends Activity {
    private static final String TAG = "RestartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String targetPackageName = getIntent().getStringExtra("package");
        String targetClassName = getIntent().getStringExtra("class");
        int flags = getIntent().getIntExtra("flags", 0);

        if (targetPackageName == null) targetPackageName = "";
        if (targetClassName == null) targetClassName = "";

        final String pkg = targetPackageName;
        final String className = targetClassName;

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            try {
                Log.i(TAG, "start activity " + pkg + "/" + className);
                Intent launchIntent = new Intent();
                launchIntent.setClassName(pkg, className);
                if (flags == 0) {
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                }
                else{
                    launchIntent.addFlags(flags);
                }
                startActivity(launchIntent);
            } catch (Exception e) {
                Log.e(TAG, "Exception:"+e.getMessage());
                e.printStackTrace();
            }
        }, 1000);

        handler.postDelayed(() -> {
            Log.i(TAG, "finish " + TAG);
            finish();
        }, 2000);
    }
}
