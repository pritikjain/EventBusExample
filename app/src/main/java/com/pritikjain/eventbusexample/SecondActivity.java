package com.pritikjain.eventbusexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pritikjain.eventbusexample.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    TextView userStatus;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        userStatus = (TextView)findViewById(R.id.user_status);
    }

    /**
     * Receiving Login Event when it happens,
     * Using Sticky = true telling the activity please go and get the last LoginEvent has been posted.
     */


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event)
    {
        userStatus.setText("User Status : Logged in , UserName: "+ event.userName);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        bus.register(this);  // register the bus
    }

    @Override
    public void onStop()
    {
        bus.unregister(this);  // unregister the bus
        super.onStop();
    }


}
