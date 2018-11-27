package application.roy.testphoneproject;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class TestService extends Service {
    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zyj","TestService ..........onCreate..........");

        mHandler.sendEmptyMessageDelayed(1,2000);

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("zyj","TestService ..........start phone service ..........");
            Intent intent =  new Intent();
            intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestPhoneService"));
            startService(intent);

            mHandler.sendEmptyMessageDelayed(1,1000);

        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zyj","TestService ..........onDestroy..........");


        Intent intent =  new Intent();
        intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestService"));
        startService(intent);

    }
}
