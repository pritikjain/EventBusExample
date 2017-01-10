package com.pritikjain.eventbusexample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pritikjain.eventbusexample.event.ChargingEvent;
import com.pritikjain.eventbusexample.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FragmentA extends Fragment {

    private EventBus bus = EventBus.getDefault();
    private TextView userStatus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);
        userStatus = (TextView) rootView.findViewById(R.id.user_status);

        return rootView;
    }

    /**
     * Receiving Login event when it happens
     */

    @Subscribe
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
