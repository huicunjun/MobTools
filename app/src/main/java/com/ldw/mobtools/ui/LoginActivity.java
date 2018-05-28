package com.ldw.mobtools.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ldw.mobtools.R;
import com.ldw.mobtools.utils.SPUtils;
import com.ldw.mobtools.utils.NetUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity implements View.OnClickListener {
    private Handler handler;
    private EditText email_ed;
    private EditText password_ed;
    private Button enter;
    private TextView join;
    private String name;
    private String password;
    private String email;
    private StringBuffer url;

    private Context context = LoginActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences =getSharedPreferences("theme",MODE_PRIVATE);
        String theme_name = preferences.getString("theme_name","哔哩哔哩");
        if (theme_name.equals("骚粉")){
            setTheme(R.style.AppTheme);
        } else if(theme_name.equals("知乎")){
            setTheme(R.style.AppTheme_zhihu);
        }  else if(theme_name.equals("网易云音乐")){
            setTheme(R.style.AppTheme_wangyi);
        } else if(theme_name.equals("酷安")){
            setTheme(R.style.AppTheme_zhihu);
        } else if(theme_name.equals("哔哩哔哩")){
            setTheme(R.style.AppTheme_zhihu);
        }

        isLogin();  //判断是否登录过
        //immersionBar();//沉浸状态栏
        initHandler();//载入Handler
        initdata();//载入数据
    }

    private void isLogin() {
       // SharedPreferences preferences = getSharedPreferences("userdata",MODE_PRIVATE);
        if (SPUtils.getBoolean(context,"userdata","login",false)){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void immersionBar() {
        /**
         * 沉浸式状态栏
         * 要在父布局设置两个属性
         * android:fitsSystemWindows="true"
         * android:clipToPadding="true"
         */
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(final Message msg) {
                switch (msg.what) {
                    case 110:
                        /**
                         *  错误码	说明
                         10001	appkey不合法
                         10020	接口维护
                         10021	接口停用
                         200	成功
                         应用级错误码参照：

                         错误码	说明
                         22801	查询不到相关数据
                         22802	username不允许为空
                         22803	password不允许为空
                         22807	用户名或密码错误
                         */
                        try {
                            final JSONObject jsonObject = (JSONObject) msg.obj;
                            Log.i("Tab", "jsonObject =" + jsonObject);
                            String retCode = jsonObject.getString("retCode");
                            Log.i("Tab", "retCode =" + retCode);
                            //jsonObject.getJSONArray()
                            switch (retCode) {
                                case "200":
                                    Log.i("Tab", "retCode =" + retCode);
                                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    SPUtils.putBoolean( LoginActivity.this,"userdata","login", true);
                                    SPUtils.putString(LoginActivity.this,"userdata","name", name);
                                    SPUtils.putString( LoginActivity.this,"userdata","email", email);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                case "22802":
                                    Toast.makeText(LoginActivity.this, "用户名不允许为空,错误码22802", Toast.LENGTH_SHORT).show();
                                    break;
                                case "22803":
                                    Toast.makeText(LoginActivity.this, "密码不允许为空,错误码22803", Toast.LENGTH_SHORT).show();
                                    break;
                                case "22807":
                                    Toast.makeText(LoginActivity.this, "用户名或密码错误,错误码22807", Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    Toast.makeText(LoginActivity.this, "服务器出错!错误码0000", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;

                }
                return false;
            }
        });
    }

    private void initdata() {
        enter = findViewById(R.id.login_enter);
        email_ed = findViewById(R.id.login_email);
        password_ed = findViewById(R.id.login_password);
        join = findViewById(R.id.login_join);

        enter.setOnClickListener(this);
        join.setOnClickListener(this);

    }

    private void Result_Get(final String url, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reasult = HttpGet(url);
                    //Toast.makeText(MobActivity.this, reasult, Toast.LENGTH_SHORT).show();
                    //Log.i("Tab",reasult);
                    JSONObject jsonObject = new JSONObject(reasult);
                    Message message = new Message();
                    message.what = 110;
                    message.obj = jsonObject;
                    handler.sendMessage(message);
                    //Toast.makeText(MobActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private String HttpGet(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_enter:
                //请求示例：http://apicloud.mob.com/user/login?key=25c1f67d8be28&username=tangsir&password=4AC36A18EA02AC6C
                if (NetUtils.isConnectIsNomarl(context)) {//这是有网时
                    name =  email_ed.getText().toString();
                    password = password_ed.getText().toString();
                    email = email_ed.getText().toString();
                    if (name.indexOf("@") > 0 && name.length() > 5 && password.length() > 6) {//账号密码格式输入正确时
                        url = new StringBuffer("http://apicloud.mob.com/user/login?key=25c1f67d8be28" + "&username=" +name +"&password=" +  password);
                        Log.i("Tab", url.toString());
                        Result_Get(url.toString(), handler);
                    } else if (name.length() < 5) {
                        Toast.makeText(this, "账号不能小于5位!", Toast.LENGTH_SHORT).show();
                    } else if ( name.contains("@")) {
                        Toast.makeText(this, "账号必须邮箱格式", Toast.LENGTH_SHORT).show();
                    } else if (password.indexOf(" ") > 0 ) {
                        Toast.makeText(this, "密码不能有空格!", Toast.LENGTH_SHORT).show();
                    }else if ( password.length() < 6) {
                        Toast.makeText(this, "密码长度不能小于6位!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "未知错误!", Toast.LENGTH_SHORT).show();
                    }
                } else { //这是没网时!!!
                    Toast.makeText(this, "当前网络不可以,亲!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_join:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                Bundle bundle = new Bundle();
                name = email_ed.getText().toString();
                bundle.putString("name", name);
                password = password_ed.getText().toString();
                bundle.putString("password", password);
                email = email_ed.getText().toString();
                bundle.putString("email", email);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
