package com.ldw.mobtools.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ldw.mobtools.R;
import com.ldw.mobtools.utils.SPUtils;

public class SettingActivity extends Activity implements View.OnClickListener{
    private RelativeLayout night;
    private SwitchCompat switch_night;
    private SharedPreferences sp;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        //View view = (View)findViewById(R.id.setting_toolbar);
        //toolbar.setNavigationIcon();
        //setActionBar(toolbar);
        initview();
        initToolBar();
        initsetting();

    }

    private void initview() {
       toolbar = findViewById(R.id.setting_toolbar);
       switch_night= (SwitchCompat)findViewById(R.id.activity_setting_switch_yjms);
        sp = getSharedPreferences("userdata",MODE_PRIVATE);
        switch_night.setOnClickListener(this);

    }

    private void initToolBar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initsetting() {
        boolean night_theme = sp.getBoolean("night_theme", false);
        switch_night.setChecked(night_theme);
        Toast.makeText(this, night_theme ? "夜间模式" : "非夜间模式", Toast.LENGTH_SHORT).show();
      /*  if (night_theme){
            Toast.makeText(this, "夜间模式", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "非夜间模式", Toast.LENGTH_SHORT).show();
        }
        //switch_night.setChecked(sp.getBoolean("night_theme",false));*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_setting_switch_yjms:
                SPUtils.putBoolean(this,"userdata","night_theme",switch_night.isChecked());
                //sp.edit().putBoolean("night_theme", switch_night.isChecked()).apply();
                break;
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
