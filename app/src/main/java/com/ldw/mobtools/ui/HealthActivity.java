package com.ldw.mobtools.ui;



import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.ldw.mobtools.R;
import com.ldw.mobtools.adapter.HealthNewsRecylerAdapter;
import com.ldw.mobtools.bean.HealthBean;
import com.ldw.mobtools.listener.RecyclerItemClickListener;
import com.ldw.mobtools.utils.GsonUtils;
import com.ldw.mobtools.utils.LogUtils;
import com.ldw.mobtools.utils.MsgUtils;
import com.ldw.mobtools.utils.OkhttpUtils;
import com.ldw.mobtools.utils.SPUtils;
import com.ldw.mobtools.values.Health;

import java.io.IOException;
import java.util.List;

public class HealthActivity extends AppCompatActivity {

    private SearchView searchView;
    private Handler handler;
    private String url;
    private String inputdata;
    private Toolbar toolbar;
    private Message message;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private List<HealthBean.ResultBean.ListBean> listBeans;
    private Gson gson = new Gson();
    private HealthBean healthBean;
    private HealthNewsRecylerAdapter adapter;
    private final Context context = HealthActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        initToolBar();
        initFindId();
        initSearch();
        initHandler();
        //初始载入数据
        initData(MsgUtils.getMsg(111,null),getSharedPreferences("healthdata",MODE_PRIVATE).getString("inputdata","樱桃"));
        initSwipe();
    }

    private void initFindId() {
        swipeRefreshLayout = findViewById(R.id.activity_health_swipe);
    }

    private void initSearch() {
        //searchView =
    }

    private void initData(final Message msg, String inputdata) {
       // inputdata = getSharedPreferences("healthdata",MODE_PRIVATE).getString("上一次输入数据","雪梨");
        url = "http://apicloud.mob.com/appstore/health/search?key=25c1f67d8be28&name="+inputdata;
        LogUtils.Logi("拼接好的URL = "+url);
            //message = new Message();
            //message.what = 111;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    String result = OkhttpUtils.http_get(url);
                    if (result.length()>35){
                    msg.obj = result;
                    LogUtils.Logi("服务器返回的结果 : "+(result));
                    handler.sendMessage(msg);
                    }else {
                        msg.what = 000;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
            }).start();
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    //初始化载入数据
                    case 111:
                        //healthBean = gson.fromJson(msg.obj.toString(),HealthBean.class);
                        healthBean = (HealthBean) GsonUtils.JsonToBean(msg.obj.toString(),HealthBean.class);
                        listBeans = healthBean.getResult().getList();
                        LogUtils.Logi("标题 =="+listBeans.get(1).getTitle());
                        LogUtils.Logi(""+listBeans.get(0).getContent());
                        LogUtils.Logi(""+listBeans.size());
                        initRecyclerView();
                        break;
                    case 222:
                        break;
                    case 000:
                        Toast.makeText(context, "垃圾服务器,出错了!!!", Toast.LENGTH_SHORT).show();
                        break;
                    case 520:
                        //用户操作 载入数据
                        healthBean = gson.fromJson(msg.obj.toString(),HealthBean.class);
                       // listBeans = healthBean.getResult().getList();
                        //initRecyclerView();
                        listBeans.clear();
                        listBeans = healthBean.getResult().getList();
                        LogUtils.Logi(listBeans.get(0).getContent());
                        //listBeans.add((HealthBean.ResultBean.ListBean) healthBean.getResult().getList());\
                        adapter.setData(listBeans);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.activity_health_rec);
        adapter = new HealthNewsRecylerAdapter(listBeans,HealthActivity.this);
        //布局管理器
         layoutManager =new LinearLayoutManager(HealthActivity.this,LinearLayoutManager.VERTICAL,false);
        //装上布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //点击事件监听
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(HealthActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
           // @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(HealthActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                new MaterialDialog.Builder(HealthActivity.this)
                        .title(listBeans.get(position).getTitle())
                        .contentColor(Color.BLACK)
                        .content(listBeans.get(position).getContent())
                        .positiveText(null)
                        .negativeText("确定")
                        .show();
            }

            @Override
            public void onLongClick(View view, int posotion) {

            }
        }));
        //
        //解决swipelayout与Recyclerview的冲突
       /* recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //状态改变时
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            //滚动时
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
   *//*             int item = recyclerView.getChildCount();
                if (item == 0||recyclerView ==null){
                    //swipeRefreshLayout.setEnabled(true);
                    initSwipe();
                }   else{
                    //swipeRefreshLayout.setEnabled(false);
                }*//*
                super.onScrolled(recyclerView, dx, dy);
            }
        });*/
        //装上适配器
        recyclerView.setAdapter(adapter);
    }

    private void initSwipe() {
        //swipeRefreshLayout.setColorSchemeColors(Color.WHITE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int math = (int) (Math.random() * Health.health_data_array.length);
                initData(MsgUtils.getMsg(520,null), Health.health_data_array[math]);
                //3s延迟执行
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //结束后停止刷新
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);

                //Toast.makeText(context, "刷新中!!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.activity_health_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(HealthActivity.this, "fanhui", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_health,menu);
        MenuItem item = menu.findItem(R.id.search);
        searchView = (SearchView) item.getActionView();
        //设置搜索栏的默认提示
        //searchView.setQueryHint("请输入关键字");
        //默认刚进去就打开搜索栏
        searchView.setIconified(true);
        //设置输入文本的EditText
        SearchView.SearchAutoComplete et = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        //设置搜索栏的默认提示，作用和setQueryHint相同
        et.setHint("请输入关键字");
        //设置提示文本的颜色
        et.setHintTextColor(Color.WHITE);
        //设置输入文本的颜色
        et.setTextColor(Color.WHITE);
        //设置提交按钮是否可见
        //searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //handler.sendMessage(MsgUtils.getMsg(520,query));
                initData(MsgUtils.getMsg(520,query),query);
               // Toast.makeText(HealthActivity.this, "你输入的是:"+query, Toast.LENGTH_SHORT).show();
                SPUtils.putString(context,"healthdata","inputdata",query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
