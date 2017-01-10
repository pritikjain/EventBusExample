package com.pritikjain.eventbusexample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;

import com.pritikjain.eventbusexample.event.ChargingEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by pritijain on 09/01/2017.
 */

public class ChargingReceiver extends BroadcastReceiver {

    private EventBus bus = EventBus.getDefault();
    @Override
    public void onReceive(Context context, Intent intent) {

        ChargingEvent event = null;

        String action = intent.getAction();

        //Get current time
        Time now = new Time();
        now.setToNow();
        String timeOfEvent = now.format("%H:%M:%S");




        String eventData = "@" + timeOfEvent + "this device started";
        if(action.equals(Intent.ACTION_POWER_CONNECTED)){
            event = new ChargingEvent(eventData+"charging.");
        }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED))
        {
            event = new ChargingEvent(eventData+"discharging");
        }

        // Post the event
        bus.post(event);

    }
}
