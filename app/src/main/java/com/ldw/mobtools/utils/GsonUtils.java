package com.ldw.mobtools.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {
    static Gson gson = new Gson();
    public static Object JsonToBean(String json,Object obj){
        return gson.fromJson(json, (Type) obj);
    }
}
