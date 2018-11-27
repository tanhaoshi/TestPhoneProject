package application.roy.testphoneproject;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

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
    }

    private Intent mIntent;

    private GuardHandle mHandle = new GuardHandle(this);

    private static class GuardHandle extends Handler{

        private WeakReference<TestService> mWeakReference;

        public GuardHandle(TestService testService){
             mWeakReference = new WeakReference<TestService>(testService);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TestService testService = mWeakReference.get();
            if(null != testService){
                Log.e("zyj","the process is com.android.phone uid ...");
                testService.startGuardService(testService.mIntent,testService.mHandle);
            }else{
                mWeakReference = new WeakReference<TestService>(testService);
                TestService service = mWeakReference.get();
                service.startGuardService(service.mIntent,service.mHandle);
            }
        }
    }

    private void startGuardService(Intent mIntent,GuardHandle guardHandle){
        if(null == mIntent){
            mIntent =  new Intent();
            mIntent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestPhoneService"));
        }
        startService(mIntent);

        guardHandle.sendEmptyMessageDelayed(1,1000);
    }

    private void startHikeService(){
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestService"));
        startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
//        当Service因为内存不足而被系统kill后，接下来未来的某个时间内，当系统内存足够可用的情况下，系统将会尝试重新创建此Service，
//        一旦创建成功后将回调onStartCommand(...)方法，但其中的Intent将是null，pendingintent除外。
        mHandle.sendEmptyMessageDelayed(1,2000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("zyj","TestService ..........onDestroy..........");
        startHikeService();
    }
}
