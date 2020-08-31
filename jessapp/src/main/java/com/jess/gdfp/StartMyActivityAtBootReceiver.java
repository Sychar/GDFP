package com.jess.gdfp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
When this class receives an intent, it checks if it is the ACTION_BOOT_COMPLETE. If so, it creates a new activity intent and fills it with the activity class to be started.
Finally, it executes the startActivity() method using the Android context and the activity intent.
*/

public class StartMyActivityAtBootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent activityIntent = new Intent(context, MainActivity.class);
            activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(activityIntent);
        }
    }
}
