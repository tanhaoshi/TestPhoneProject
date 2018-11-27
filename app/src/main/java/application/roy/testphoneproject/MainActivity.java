package application.roy.testphoneproject;

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
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("zyj","MainActivity ..........onStop..........");
    }
}
