package com.ldw.mobtools.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SPUtils {
    private static SharedPreferences preferences;

    public static void putString(Context context, String filename, String key, String str){
        preferences = context.getSharedPreferences(filename, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,str);
        editor.apply();
    }
    public static void putBoolean(Context context, String filename, String key, Boolean str){
        preferences = context.getSharedPreferences(filename, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,str);
        editor.apply();
    }
    public static void putInt(Context context, String filename, String key, int str){
        preferences = context.getSharedPreferences(filename, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,str);
        editor.apply();
    }
    public static String getString(Context context, String filename, String key, String str){
        preferences = context.getSharedPreferences(filename, MODE_PRIVATE);
        return preferences.getString(key,str);
    }

    public static boolean getBoolean(Context context, String filename, String key, Boolean str) {
        preferences = context.getSharedPreferences(filename, MODE_PRIVATE);
        return preferences.getBoolean(key,str);
    }
    /**
     *
     * 使用步骤：

     1、得到SharedPreferences对象
     2、调用SharedPreferences对象的edit()方法来获取一个SharedPreferences.Editor对象。
     3、向SharedPreferences.Editor对象中添加数据。
     4、调用commit方法将添加的数据提交。
     详细过程：

     1、得到SharedPreferences对象
     方法1：
     Context.getSharedPreferences(文件名称，操作模式)
     文件名称不存在就会创建一个，操作模式有两种：
     MODE_PRIVATE：默认操作模式，直接在把第二个参数写0就是默认使用这种操作模式，这种模式表示只有当前的应用程序才可以对当前这个SharedPreferences文件进行读写。
     MODE_MULTI_PRIVATE：用于多个进程共同操作一个SharedPreferences文件。

     注：MODE_WORLD_READABLE和MODE_WORLD_WRITEABLE这两种模式已经在android 4.2版本以后废弃了。
     方法2：
     Activity.getPreferences(操作模式)
     使用这个方法会自动将当前活动的类名作为SharedPreferences的文件名，底层调用的是下面这个方法
     Activity.getSharedPreferences（String name, int mode）我们也可以直接调用getSharedPreferences这个方法，传入自定义的名字。
     方法3
     PreferenceManager.getDefaultSharedPreferences(Context)
     使用这个方法会自动使用当前程序的包名作为前缀来命名SharedPreferences文件

     2、调用SharedPreferences对象的edit()方法来获取一个SharedPreferences.Editor对象。
     SharedPreferences.Editor editor = getSharedPreferences(“data”,MODE_PRIVATE).edit();

     3、向SharedPreferences.Editor对象中添加数据。
     editor.putString(“name”,”Lily”); //以键值对形式存储
     editor.putInt(“age”,”20”); //以键值对形式存储
     editor.putBoolean(“married”,false); //以键值对形式存储

     4、调用commit方法将添加的数据提交。
     editor.commit();

     代码示例：
     SharedPreferences pref = MainActivity.this.getSharedPreferences(“data”,MODE_PRIVATE);
     SharedPreferences.Editor editor = pref.edit();
     editor.putString(“name”,”lily”);
     editor.putString(“age”,”20”);
     editor.putBoolean(“married”,false);
     editor.commit();

     如果要读取数据：
     SharedPreferences pref = getSharedPreferences(“data”,MODE_PRIVATE);
     String name = pref.getString(“name”,”“);//第二个参数为默认值
     int age = pref.getInt(“age”,0);//第二个参数为默认值
     boolean married = pref.getBoolean(“married”,false);//第二个参数为默认值

     保存对象：
     Android使用SharedPreferences保存对象 - 推酷
     http://www.tuicool.com/articles/6nAZrq

     保存list:
     可以将list转化为json字符串，然后以string的形式保存，使用的时候先取出这个string再解析json得到list。
     */
}
