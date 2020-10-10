package com.example.fort.listsFolder.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.fort.listsFolder.PatternLockActivity;
import com.example.fort.listsFolder.utils.Utils;

public class ReceiverApplock extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Utils utils = new Utils(context);
        String appRunning = utils.getLauncherTopApp();

        if(utils.isLock(appRunning))
        {
            if(!appRunning.equals(utils.getLastApp()))
            {
                utils.clearLastApp();
                utils.setLastApp(appRunning);

                Intent i = new Intent(context, PatternLockActivity.class);
                i.putExtra("broadcast_reciever","broadcast_reciever");
                context.startActivity(i);
            }
        }

    }
}
