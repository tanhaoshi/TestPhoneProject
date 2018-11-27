package application.roy.testphoneproject;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,TestService.class));
        /*Intent intent =  new Intent();
        intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestService"));
        startService(intent);*/


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zyj","MainActivity ..........onStop..........");

        /*Intent intent =  new Intent();
        intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestService"));
        startService(intent);*/
       // stopService(new Intent(this,TestService.class));
/*
       Intent intent =  new Intent();
       intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestPhoneService"));
       stopService(intent);*/
    }
}
