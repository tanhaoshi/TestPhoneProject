package application.roy.testphoneproject;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestPhoneService extends Service {

    //这个服务并没有做拉起另一个服务的动作,这个服务也就说在做业务处理这一块
    //
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
         //START_STICKY
//       当Service因为内存不足而被系统kill后，接下来未来的某个时间内，当系统内存足够可用的情况下，系统将会尝试重新创建此Service，
//       一旦创建成功后将回调onStartCommand(...)方法，但其中的Intent将是null，pendingintent除外。
        Log.e("zyj","TestPhoneService onStartCommand effect ...");
        // TestService 只是在每次从新启动服务时会来这提高当前服务的级别,将当前服务提的级别非常高.
        //这里我会从新检测下 我的testService服务的存活
        if(!AppUtils.isServiceRuning("application.roy.testphoneproject.TestService",this)){
            startGuardService();
        }
        return START_STICKY;
    }

    private void startGuardService(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestService"));
        startService(intent);
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
