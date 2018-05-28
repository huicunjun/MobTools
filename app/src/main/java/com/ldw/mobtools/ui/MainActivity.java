package com.ldw.mobtools.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ldw.mobtools.R;
import com.ldw.mobtools.adapter.FragAdapter;
import com.ldw.mobtools.fragment.HomeFragment;
import com.ldw.mobtools.fragment.OtherFragment;
import com.ldw.mobtools.utils.LogUtils;
import com.ldw.mobtools.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private SharedPreferences sp;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initsetting();      //载入主题
        setContentView(R.layout.activity_main);
        //immersionBar();//沉浸状态栏
        initView();     //载入视图
        initFamousSentence();   //载入古诗词
        initOnClick();
    }

    @Override
    protected void onStart() {
        LogUtils.Logi("onStart方法");
        //Toast.makeText(this, "onStart方法", Toast.LENGTH_SHORT).show();
        initsetting();
        super.onStart();
    }

    private void initsetting() {
        sp = getSharedPreferences("userdata", MODE_PRIVATE);
        boolean night_theme = sp.getBoolean("night_theme", false);
        if (night_theme) {
            MainActivity.this.setTheme(R.style.NightTheme);
            LogUtils.Logi("夜间模式");
            //Toast.makeText(this, "夜间模式", Toast.LENGTH_SHORT).show();
        } else {
            LogUtils.Logi("不是夜间模式");
            //Toast.makeText(this, "非夜间模式", Toast.LENGTH_SHORT).show();
        }
        //switch_night.setChecked(sp.getBoolean("night_theme",false));
    }

    private void initOnClick() {
        //更多点击事件
        ImageView more_image = findViewById(R.id.main_more);
        more_image.setOnClickListener(this);
    }

    /**
     * 载入古诗词
     */
    private void initFamousSentence() {
        List<String> gushis = new ArrayList<>();
        TextView subtitle = findViewById(R.id.main_gushi);
        gushis.add("三军可夺帅也，匹夫不可夺志也。");
        gushis.add("山有木兮木有枝，心悦君兮君不知。");
        gushis.add("曾经沧海难为水，除却巫山不是云。");
        gushis.add("玲珑骰子安红豆，入骨相思知不知。");
        gushis.add("只愿君心似我心，定不负相思意。");
        gushis.add("入我相思门，知我相思苦。");
        gushis.add("春宵一刻值千金，花有清香月有阴。");
        gushis.add("露从今夜白，月是故乡明。");
        gushis.add("二十四桥明月夜，玉人何处教吹箫？");
        gushis.add("春江潮水连海平，海上明月共潮生。");
        gushis.add("世间无限丹青手，一片伤心画不成。");
        gushis.add("苟利国家生死以，岂因祸福避趋之！");
        int math = (int) (Math.random() * (gushis.size()));
        //Toast.makeText(this, math, Toast.LENGTH_SHORT).show();
        Log.i("Tag", "" + math);
        subtitle.setText(gushis.get(math));

    }

    private void initView() {
        ViewPager viewPager = findViewById(R.id.main_viewpager);
        TabLayout tabLayout = findViewById(R.id.main_tablayout);
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new OtherFragment());
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(fragAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("主页");//.setIcon(R.mipmap.main_home);
        tabLayout.getTabAt(1).setText("其他");//.setIcon(R.mipmap.main_other);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_more:
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_about:
                                Toast.makeText(MainActivity.this, "关于", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.action_settings:
                                //Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                                intent = new Intent(MainActivity.this, SettingActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.action_cancel:
                                //Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                                SPUtils.putBoolean(MainActivity.this,"userdata","login",false);
                               // LogUtils.Logi("login = "+(getSharedPreferences("userdata",MODE_PRIVATE).getBoolean("login",false)));
                                intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                        }
                        return false;
                    }
                });
                break;
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

    /**
     * 该方法是用来加载菜单布局的
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toobar跟menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
