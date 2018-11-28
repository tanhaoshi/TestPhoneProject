package application.roy.testphoneproject;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

public class TestPhoneService extends Service {

    //这个服务并没有做拉起另一个服务的动作,这个服务也就说在做业务处理这一块
    public TestPhoneService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private TaskThread mThread;

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        Log.e("zyj","TestPhoneService ..........onCreate..........");
        mThread = new TaskThread(this);
        mThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        //START_STICKY 提高存活率
        Log.e("zyj","TestPhoneService onStartCommand effect ...");
        return START_STICKY;
    }

    public static class TaskThread extends Thread{

        private WeakReference<TestPhoneService> mWeakReference;

        public TaskThread(TestPhoneService testPhoneService){
             mWeakReference = new WeakReference<TestPhoneService>(testPhoneService);
        }

        @Override
        public void run() {
            super.run();
            TestPhoneService testPhoneService = mWeakReference.get();
            if(null != testPhoneService){
                try{
                    while (true){
                        Thread.sleep(1000);
                        Log.e("zyj","testt ....................");
                        testPhoneService.checkTaskService();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                EventBus.getDefault().post(new MessageEventBus(IMessageEventBustType.EVENT_BUS_TYPE_NOTIFY_WAKEUP_THREAD));
            }
        }
    }

    private void checkTaskService(){
        Log.e("zyj","TestPhoneService look over task service is runing ? ");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEventBus event) {
        switch (event.getType()) {
            case IMessageEventBustType.EVENT_BUS_TYPE_NOTIFY_WAKEUP_THREAD:
                mThread = null;
                mThread = new TaskThread(this);
                mThread.start();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e("zyj","TestPhoneService ..........onDestroy..........");
        if(mThread != null && mThread.isAlive()){
            mThread.interrupt();
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("application.roy.testphoneproject","application.roy.testphoneproject.TestPhoneService"));
        startService(intent);
    }
}
