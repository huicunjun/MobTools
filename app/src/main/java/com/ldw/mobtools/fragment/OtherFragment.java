package com.ldw.mobtools.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.ldw.mobtools.R;
import com.ldw.mobtools.bean.CalendarBean;
import com.ldw.mobtools.utils.LogUtils;
import com.ldw.mobtools.utils.MsgUtils;
import com.ldw.mobtools.utils.OkhttpUtils;
import com.ldw.mobtools.utils.SPUtils;
import java.io.IOException;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {
    private Context context = getContext();
    private Handler handler;
    private Gson gson;


    private View view;
    private TextView tv_date, tv_holiday, tv_lunar, tv_lunarYear, tv_suit, tv_weekday, tv_zodiac,tv_avoid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_two, container, false);
        initUserData();     //载入用户数据
      //  initCalendar();     //载入日历
        return view;
    }

    private void initCalendar() {
        initHandler();
        initData();
        initSpData();
    }

    private void initUserData() {
        SharedPreferences preferences = Objects.requireNonNull(getActivity()).getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String name = preferences.getString("name", "广职周柏豪");
        String email = preferences.getString("email","1214585092@qq.com");
        TextView tv_name = view.findViewById(R.id.frag_two_name);
        TextView tv_email = view.findViewById(R.id.frag2_email);
        //tv_name.setText(name);
        tv_email.setText(email);
    }
    public void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 11111:
                        CalendarBean calendarBean = gson.fromJson(msg.obj.toString(),CalendarBean.class);
                        tv_date.setText(calendarBean.getResult().getDate());
                        tv_weekday.setText(calendarBean.getResult().getWeekday());
                        tv_suit.setText(calendarBean.getResult().getSuit());
                        tv_avoid.setText(calendarBean.getResult().getAvoid());
                        tv_holiday.setText(calendarBean.getResult().getHoliday() == null ? "非节日":calendarBean.getResult().getHoliday());
                        tv_lunar.setText(calendarBean.getResult().getLunar());
                        tv_lunarYear.setText(calendarBean.getResult().getLunarYear());
                        tv_zodiac.setText(calendarBean.getResult().getZodiac());
                        //Toast.makeText(context, "日期格式错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 21001:
                        Toast.makeText(context, "日期格式错误", Toast.LENGTH_SHORT).show();
                        break;
                    case 10020:
                        Toast.makeText(context, "接口维护", Toast.LENGTH_SHORT).show();
                        break;
                    case 10021:
                        Toast.makeText(context, "接口停用", Toast.LENGTH_SHORT).show();
                        break;
                    case 21003:
                        Toast.makeText(context, "无对应参数的数据返回", Toast.LENGTH_SHORT).show();
                        break;
                    case 21004:
                        Toast.makeText(context, "查询的日期范围有误", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "服务器错误!!!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    public void initData() {
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_calendar,null);
        tv_date = layout.findViewById(R.id.calendar_data);
        tv_holiday = layout.findViewById(R.id.calendar_holiday);
        tv_lunar = layout.findViewById(R.id.calendar_lunar);
        tv_lunarYear = layout.findViewById(R.id.calendar_lunarYear);
        tv_suit = layout.findViewById(R.id.calendar_suit);
        tv_weekday = layout.findViewById(R.id.calendar_weekday);
        tv_zodiac = layout.findViewById(R.id.calendar_zodiac);
        tv_avoid = layout.findViewById(R.id.calendar_avoid);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(context)
                        .title("日期")
                        .inputType(InputType.TYPE_CLASS_NUMBER)
                        .input("请输入年月日:20180526","",new MaterialDialog.InputCallback(){

                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                //输入格式正确
                                if (input.toString().length() == 8){

                                    final StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append(input.toString()).insert(4, "-").insert(7,"-");
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                String result = OkhttpUtils.http_get("http://apicloud.mob.com/appstore/calendar/day?key=25c1f67d8be28&date="+stringBuffer.toString());
                                                SPUtils.putString(context,"calendardata","日期",stringBuffer.toString());     //保存到SP
                                                LogUtils.Logi("转换好的日期格式 = "+stringBuffer.toString());
                                                LogUtils.Logi("服务器返回结果 = "+result);
                                                if (result.indexOf("21001")>0){      //服务器返回的查询的日期格式错误
                                                    //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                                                    handler.sendMessage(MsgUtils.getMsg(21001,null));
                                                }   else if (result.indexOf("10020")>0){        //接口维护
                                                    handler.sendMessage(MsgUtils.getMsg(10020,"接口维护"));
                                                } else if (result.indexOf("10021")>0){        //接口停用
                                                    handler.sendMessage(MsgUtils.   getMsg(10020,"接口停用"));
                                                } else if (result.indexOf("21003")>0){        //查询的日期格式错误,格式:yyyy-MM-dd
                                                    handler.sendMessage(MsgUtils.getMsg(21003,"无对应参数的数据返回"));
                                                }
                                                else if (result.indexOf("21004")>0){        //查询的日期格式错误,格式:yyyy-MM-dd
                                                    handler.sendMessage(MsgUtils.getMsg(21004,"查询的日期范围有误"));
                                                } else if (result.length()>20){        //查询的日期格式错误,格式:yyyy-MM-dd
                                                    handler.sendMessage(MsgUtils.getMsg(11111,result));
                                                }
                                                else {
                                                    handler.sendMessage(MsgUtils.getMsg(0,null));
                                                }


                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();

                                }else {     //  真的输入错误!!!
                                    Toast.makeText(context, "日期格式错误!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });


    }

    public void initSpData() {
        final String url = SPUtils.getString(context,"calendardata","日期","1999-06-22");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = OkhttpUtils.http_get("http://apicloud.mob.com/appstore/calendar/day?key=25c1f67d8be28&date="+url);
                    LogUtils.Logi("转换好的日期格式 = "+url);
                    LogUtils.Logi("服务器返回结果 = "+result);
                    if (result.indexOf("21001")>0){      //服务器返回的查询的日期格式错误
                        //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                        handler.sendMessage(MsgUtils.getMsg(21001,null));
                    }   else if (result.indexOf("10020")>0){        //接口维护
                        handler.sendMessage(MsgUtils.getMsg(10020,"接口维护"));
                    } else if (result.indexOf("10021")>0){        //接口停用
                        handler.sendMessage(MsgUtils.   getMsg(10020,"接口停用"));
                    } else if (result.indexOf("21003")>0){        //查询的日期格式错误,格式:yyyy-MM-dd
                        handler.sendMessage(MsgUtils.getMsg(21003,"无对应参数的数据返回"));
                    }
                    else if (result.indexOf("21004")>0){        //查询的日期格式错误,格式:yyyy-MM-dd
                        handler.sendMessage(MsgUtils.getMsg(21004,"查询的日期范围有误"));
                    } else if (result.length()>20){        //查询的日期格式错误,格式:yyyy-MM-dd
                        handler.sendMessage(MsgUtils.getMsg(11111,result));
                    }
                    else {
                        handler.sendMessage(MsgUtils.getMsg(0,null));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
