package com.ldw.mobtools.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.ldw.mobtools.R;
import com.ldw.mobtools.adapter.WeatherRecyclerViewAdapter;
import com.ldw.mobtools.bean.WeatherData;
import com.ldw.mobtools.fragment.SearchFragment;
import com.ldw.mobtools.listener.RecyclerItemClickListener;
import com.ldw.mobtools.utils.LogUtils;
import com.ldw.mobtools.utils.OkhttpUtils;
import com.ldw.mobtools.utils.SPUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends FragmentActivity implements View.OnClickListener {
    private SearchView searchView;
    private Handler handler;
    private List<WeatherData.ResultBean.FutureBean> list = new ArrayList<>();

    private TextView tv_city;
    private TextView tv_wendu;
    private Gson gson = new Gson();
    private Context context = WeatherActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initToolBar();      //载入ToolBar
        inithandler();      //初始化handler

        initdata();         //载入初始数据
        initCityListener();           //载入城市设置设置点击事件


    }

    private void initdata() {
        tv_city = findViewById(R.id.weather_city);
        tv_city.setText(getSharedPreferences("weatherdata", MODE_PRIVATE).getString("城市", "珠海"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("weatherdata", MODE_PRIVATE);
                String url = "http://apicloud.mob.com/v1/weather/query?key=25c1f67d8be28&city=" + sp.getString("城市", "珠海") + "&province=" + sp.getString("省份", "广东");
                LogUtils.Logi(sp.getString("城市", "珠海"));
                try {
                    Message message = new Message();
                    message.what = 120;
                    message.obj = OkhttpUtils.http_get(url);
                    LogUtils.Logi("拼接好的URL = " + url);
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }).start();
    }

    private void inithandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    //处理城市点击事件
                    case 110:
                        if (msg.obj.toString().length() > 70) {
                            WeatherData weatherData = gson.fromJson((String) msg.obj, WeatherData.class);
                            list = weatherData.getResult().get(weatherData.getResult().size() - 1).getFuture();

                            //initRecyclerView(); //载入RecylerView视图
                        } else {
                            Toast.makeText(WeatherActivity.this, "MOB服务器出事了,空空如也", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    //处理输入错误
                    case 000:
                        Toast.makeText(WeatherActivity.this, "输入错误,亲!", Toast.LENGTH_SHORT).show();
                        break;
                    //处理载入天气数据
                    case 120:

                        if (msg.obj.toString().length() > 70) {
                            WeatherData weatherData = gson.fromJson((String) msg.obj, WeatherData.class);
                            list = weatherData.getResult().get(weatherData.getResult().size() - 1).getFuture();
                            initRecyclerView(); //载入RecylerView视图
                        } else {
                            Toast.makeText(WeatherActivity.this, "MOB服务器出事了,空空如也", Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(WeatherActivity.this, weatherData., Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }

    /**
     * 载入视图
     */
    private void initRecyclerView() {
        //初始化列表
        //查找id
        RecyclerView recyclerView = findViewById(R.id.weather_rec);
        //创建适配器
        WeatherRecyclerViewAdapter adapter = new WeatherRecyclerViewAdapter(WeatherActivity.this, list);
        //创建布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //装上布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //添加事件监听
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(WeatherActivity.this, "haha", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int posotion) {

            }
        }));
        //装上适配器
        recyclerView.setAdapter(adapter);
        //按照ScrollView去滑动
        //recyclerView.setNestedScrollingEnabled(true);
    }

    private void initSearch() {
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //必需继承FragmentActivity,嵌套fragment只需要这行代码
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commitAllowingStateLoss();
            }
        });
    }

    /**
     * 载入ToolBar
     */
    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.activity_weather_toolbar);
        //左边返回键点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //右边设置按钮点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(WeatherActivity.this, "更多", Toast.LENGTH_SHORT).show();
                        break;
             /*       case R.id.action_weather_setting:
                        Toast.makeText(WeatherActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;*/
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        //找到searchView
        MenuItem searchItem = menu.findItem(R.id.action_weather_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        initSearch();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    //载入城市设置设置点击事件
    private void initCityListener() {

        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(WeatherActivity.this)
                        .title("选择城市")
                        .content("")
                        .inputType(InputType.TYPE_CLASS_TEXT)// InputType.TYPE_TEXT_VARIATION_PASSWORD
                        .input("请输入省份名+空格+城市名", SPUtils.getString(context, "weatherdata", "省份", "广东") + " " + SPUtils.getString(context, "weatherdata", "城市", "佛山"), new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, final CharSequence input) {
                                String[] strings = input.toString().split(" ");
                                //输入错误
                                if (strings.length > 2 || strings.length < 2) {
                                    Message message = new Message();
                                    message.what = 000;
                                    message.obj = "输入错误,亲!";
                                    handler.sendMessage(message);
                                } else {//输入正确
                                    SharedPreferences sp = getSharedPreferences("weatherdata", MODE_PRIVATE);
                                    sp.edit().putString("省份", strings[0]).apply();
                                    sp.edit().putString("城市", strings[1]).apply();
                                    tv_city.setText(input.toString());
                                    final String url = "http://apicloud.mob.com/v1/weather/query?key=25c1f67d8be28&city=" + strings[1] + "&province=" + strings[0];

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Message message = new Message();
                                                message.what = 110;
                                                message.obj = OkhttpUtils.http_get(url);
                                                handler.sendMessage(message);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();

                                }


                                //http://apicloud.mob.com/v1/weather/query?key=appkey&city=通州&province=北京
                                // Do something
                                //Toast.makeText(WeatherActivity.this, input.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                // Toast.makeText(WeatherActivity.this, "请稍等片刻...", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
