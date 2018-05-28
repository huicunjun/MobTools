package com.ldw.mobtools.utils;

import android.os.Message;

public class MsgUtils {
    private static Message message;
    public static Message getMsg(int what,Object obj){
        message = new Message();
        message.what = what;
        message.obj = obj;
        return message;
    }
}
