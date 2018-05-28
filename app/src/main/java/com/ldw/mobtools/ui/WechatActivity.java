package com.ldw.mobtools.ui;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ldw.mobtools.R;
import com.ldw.mobtools.adapter.FragAdapter;
import com.ldw.mobtools.bean.WechatClassifyBean;
import com.ldw.mobtools.fragment.wechat.FashionFragment;
import com.ldw.mobtools.utils.LogUtils;
import com.ldw.mobtools.utils.MsgUtils;
import com.ldw.mobtools.utils.OkhttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WechatActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Context context = WechatActivity.this;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<android.support.v4.app.Fragment> fraglist  = new ArrayList<>();     //framgent集合
    private List<WechatClassifyBean.ResultBean> titlelist;     //标题集合
    private List<String> datalist; //内容集合

    private Handler handler;
    private Gson gson = new Gson();
    private FragAdapter fragAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        initToolBar();
        initFindId();
        initHandler();
        initTitleData();
    }

    private void initFindId() {
        tabLayout = findViewById(R.id.wechat_tablayout);
        viewPager = findViewById(R.id.wechat_viewpager);
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    //获取标题
                    case 1:
                        LogUtils.Logi("服务器返回的内容"+msg.obj.toString());
                        WechatClassifyBean classifyBean = gson.fromJson(msg.obj.toString(),WechatClassifyBean.class);
                        titlelist = classifyBean.getResult();
                        LogUtils.Logi("打印一下title_list集合的大小: "+titlelist.size());
                        for (int i = 0;i<titlelist.size();i++){
                            fraglist.add(new FashionFragment());

                        }
                        fragAdapter = new FragAdapter(getSupportFragmentManager(), fraglist);
                        viewPager.setAdapter(fragAdapter);
                        tabLayout.setupWithViewPager(viewPager);
                       for (int i = 0;i<titlelist.size();i++){
                            LogUtils.Logi("打印一下标题: "+titlelist.get(0).getName());
                           //tabLayout.getTabAt(i).setText("是否");
                           Objects.requireNonNull(tabLayout.getTabAt(i)).setText(titlelist.get(i).getName());
                        }
                        break;
                    case 0:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initTitleData() {
        final String url = "http://apicloud.mob.com/wx/article/category/query?key=25c1f67d8be28";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = OkhttpUtils.http_get(url);
                    if (result.length()>20) {
                        handler.sendMessage(MsgUtils.getMsg(1, result));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.activity_calendar_toolbar);
        toolbar.setTitle("微信精选");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
