package com.pritikjain.eventbusexample.event;

/**
 * Created by pritijain on 09/01/2017.
 */

public class ChargingEvent {

    private String data;

    public ChargingEvent(String data)
    {
        this.data = data;
    }


    public String getData() {
        return data;
    }
}
