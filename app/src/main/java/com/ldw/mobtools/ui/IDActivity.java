package com.ldw.mobtools.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ldw.mobtools.R;
import com.ldw.mobtools.bean.IdBean;
import com.ldw.mobtools.utils.LogUtils;
import com.ldw.mobtools.utils.MsgUtils;
import com.ldw.mobtools.utils.OkhttpUtils;

import java.io.IOException;

public class IDActivity extends AppCompatActivity {

    private TextView tv_name,tv_sex,tv_year,tv_month,tv_day,tv_adress,tv_number,editText,button;
    private Handler handler;

    private Gson gson = new Gson();
    //private TextView name,sex,nationality,birth,adress,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
        initToolBar();
        initHandler();
        initData();

    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        IdBean idBean = gson.fromJson(msg.obj.toString(),IdBean.class);
                        tv_sex.setText(idBean.getResult().getSex());
                        String birthday = idBean.getResult().getBirthday();
                        tv_year.setText(birthday.substring(0,4));
                        tv_month.setText(birthday.substring(5,7));
                        tv_day.setText(birthday.substring(8,10));
                        tv_adress.setText(idBean.getResult().getArea());
                        break;
                    case 0:
                        Toast.makeText(IDActivity.this, "错误的身份证或无结果", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void initData() {
         tv_name = findViewById(R.id.id_name);
         tv_sex = findViewById(R.id.id_sex);
         tv_year = findViewById(R.id.id_year);
        tv_month = findViewById(R.id.id_month);
         tv_day = findViewById(R.id.id_day);
         tv_adress = findViewById(R.id.id_adress);
         tv_number = findViewById(R.id.id_number);
         editText = findViewById(R.id.id_edit);
         button = findViewById(R.id.id_enter);

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String number = editText.getText().toString();
                 final String url = "http://apicloud.mob.com/idcard/query?key=25c1f67d8be28&cardno="+number;
                 LogUtils.Logi("拼接好的URL = "+url);
                 new Thread(new Runnable() {
                     @Override
                     public void run() {
                         try {
                             String result = OkhttpUtils.http_get(url);
                             LogUtils.Logi("返回的结果 = "+result);
                             if (result.length()>25) {
                                 handler.sendMessage(MsgUtils.getMsg(1, result));
                             }else {
                                 handler.sendMessage(MsgUtils.getMsg(0,null));
                             }
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                 }).start();
             }
         });

    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.activity_id_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
