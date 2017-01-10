package com.pritikjain.eventbusexample;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pritikjain.eventbusexample.event.ChargingEvent;
import com.pritikjain.eventbusexample.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FirstActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button secondActivityBtn;
    private EditText userName;
    private TextView chargingView;



    private EventBus bus = EventBus.getDefault();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        loginBtn = (Button)findViewById(R.id.login_btn);
        secondActivityBtn = (Button)findViewById(R.id.second_activity_btn);
        userName = (EditText)findViewById(R.id.user_name);
        chargingView = (TextView)findViewById(R.id.chargingView);




        //That would show the FragmentA into the frameLayout
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,new FragmentA())
                .commit();

        loginBtn.setOnClickListener(new View.OnClickListener(){

                                        @Override
                                        public void onClick(View view) {
                                            // if userName was empty, then show an error
                                            // else, login user and send logged userName to all subscribers
                                            if(userName.getText().toString().isEmpty()){
                                                userName.setError("Please enter UserName");
                                            } else {
                                                /**
                                                 * send this event to all subscribers
                                                 * We use here post sticky because secondActivity is not registered yet in the bus
                                                 * (Registration happens when activity created) and it will not have any updates
                                                 * if we posted event normally not sticky
                                                 */
                                            bus.postSticky(new LoginEvent(userName.getText().toString()));
                                            }

                                        }
                                    }
        );

        secondActivityBtn.setOnClickListener(new View.OnClickListener() {

                                                 @Override
                                                 public void onClick(View view) {
                                                     startActivity(new Intent(FirstActivity.this,SecondActivity.class));
                                                 }
                                             }
        );



    }

    @Subscribe
    public void onChargingEvent(ChargingEvent event){
        chargingView.setText(chargingView.getText()+" "+event.getData());
    }

    @Override
    public void onStart()
    {
        super.onStart();
        // Charging Event
        bus.register(this);
    }

    @Override
    public void onStop()
    {
        bus.unregister(this);
        super.onStop();
    }
}
