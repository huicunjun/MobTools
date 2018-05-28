package com.ldw.mobtools.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ldw.mobtools.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private Handler handler;
    private EditText name_ed;
    private EditText email_ed;
    private EditText password_ed;
    private String name;
    private String password;
    private String email;
    private StringBuffer url;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initHandler();//载入Handler
        initdata();//载入输入数据

    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(final Message msg) {
                switch (msg.what) {
                    case 100:
                                try {
                                    final JSONObject jsonObject = (JSONObject) msg.obj;
                                    Log.i("Tab", "jsonObject =" + jsonObject);
                                    String msg1 = jsonObject.getString("msg");
                                    // Log.i("Tab",msg1);
                                    //String result = jsonObject.getString("result");
                                    String retCode = jsonObject.getString("retCode");
                                    Log.i("Tab", "retCode =" + jsonObject.getString("retCode"));
                                    switch (retCode) {
                                        case "200":
                                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "22802":
                                            Toast.makeText(RegisterActivity.this, "用户名不允许为空", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "22803":
                                            Toast.makeText(RegisterActivity.this, "密码不允许为空", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "22806":
                                            Log.i("Tab", msg1);
                                            Toast.makeText(RegisterActivity.this, "此用户名已存在", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "22807":
                                            Toast.makeText(RegisterActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "22810":
                                            Toast.makeText(RegisterActivity.this, "用户未登录或token已过期", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "22818":
                                            Toast.makeText(RegisterActivity.this, "email不符合邮箱格式", Toast.LENGTH_SHORT).show();
                                            break;
                                        case "22823":
                                            Toast.makeText(RegisterActivity.this, "此邮箱已存在", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                    /**
                                     * 系统级错误码参照：

                                     错误码	说明
                                     10001	appkey不合法
                                     10020	接口维护
                                     10021	接口停用
                                     200	成功
                                     应用级错误码参照：

                                     错误码	说明
                                     22802	username不允许为空
                                     22803	password不允许为空
                                     22806	此用户名已存在
                                     22807	用户名或密码错误
                                     22810	用户未登录或token已过期
                                     22817	email不允许为空
                                     22818	email不符合邮箱格式
                                     22823	此邮箱已存在
                                     */
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
        //查找id
        Button enter = findViewById(R.id.register_enter);
        name_ed = findViewById(R.id.register_name);
        email_ed = findViewById(R.id.register_email);
        password_ed = findViewById(R.id.register_password);
        //拿到LoginActivity传递过来的Bundle袋子
        Bundle bundle = this.getIntent().getExtras();
        //赋值操作
        name_ed.setText(bundle.getString("name",""));
        //email_ed.setText(bundle.getString("email",""));
        password_ed.setText(bundle.getString("password",""));
        enter.setOnClickListener(this);
    }

    private void Result_Check(final String url, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String reasult = HttpGet(url);
                    //Toast.makeText(MobActivity.this, reasult, Toast.LENGTH_SHORT).show();
                    //Log.i("Tab",reasult);
                    JSONObject jsonObject = new JSONObject(reasult);
                    Message message = new Message();
                    message.what = 100;
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
            case R.id.register_enter:
                //http://apicloud.mob.com/user/rigister?key=25c1f67d8be28&username=121@qq.com&password=13133313&email=121@qq.com
                name = "&username=" + name_ed.getText().toString();
                password = "&password=" + password_ed.getText().toString();
                email = "&email=" + email_ed.getText().toString();
                url = new StringBuffer("http://apicloud.mob.com/user/rigister?key=25c1f67d8be28" + name + password + email);
                Log.i("Tab", url.toString());
                Result_Check(url.toString(), handler);
                break;
        }
    }
}
