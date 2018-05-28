package com.ldw.mobtools.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetUtils {
    private static Context context;

    /** 判断网络是否连接 */
    public static boolean isConnectIsNomarl(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String intentName = info.getTypeName();
            Log.i("Tab", "当前网络名称：" + intentName);
            return true;
        } else {
            Log.i("Tab", "没有可用网络");
            return false;
        }
    }

}
