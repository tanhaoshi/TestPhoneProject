package application.roy.testphoneproject;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestPhoneService extends Service {
    public TestPhoneService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Thread mThread;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("zyj","TestPhoneService ..........onCreate..........");
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                   while (true){
                       Thread.sleep(1000);

                       Log.e("zyj","testt ....................");
                   }

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        mThread.start();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zyj","TestPhoneService ..........onDestroy..........");

        if(mThread != null && mThread.isAlive()){
            mThread.interrupt();
        }
        Intent intent =  new Intent();
        intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestPhoneService"));
        startService(intent);

    }
}
