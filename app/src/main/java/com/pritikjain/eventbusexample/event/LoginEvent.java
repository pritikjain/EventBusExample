package com.pritikjain.eventbusexample.event;

/**
 * Created by pritijain on 09/01/2017.
 */

public class LoginEvent {
    public final String userName;

    public LoginEvent(String userName)
    {
        this.userName = userName;
    }
}
