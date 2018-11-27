package application.roy.testphoneproject;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public final class AppUtils {

    /**
     * 判断服务是否处于运行状态
     */
    public static boolean isServiceRuning(String serviceName, Context context){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> infos = activityManager.getRunningServices(100);
        for(ActivityManager.RunningServiceInfo info : infos){
            if(serviceName.equals(info.service.getClassName())){
                return true;
            }
        }
        return false;
    }
}
