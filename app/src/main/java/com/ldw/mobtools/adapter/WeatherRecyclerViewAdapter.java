package com.ldw.mobtools.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldw.mobtools.R;
import com.ldw.mobtools.bean.WeatherData;
import com.ldw.mobtools.utils.LogUtils;

import java.util.List;
import java.util.Map;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<WeatherData.ResultBean.FutureBean> futureBeanList;
    //private List<WeatherData.ResultBean.FutureBean> futureBeanList;


    public WeatherRecyclerViewAdapter(Context context, List<WeatherData.ResultBean.FutureBean> futureBeanList) {
        this.context = context;
        this.futureBeanList = futureBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.layout_item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (futureBeanList.size()>0) {
            String data = futureBeanList.get(position).getDate();
            String day_time = futureBeanList.get(position).getDayTime();
            String night = futureBeanList.get(position).getNight();
            String temperature = futureBeanList.get(position).getTemperature();
            holder.tv_data.setText(data);
            holder.tv_day_time.setText(day_time);
            holder.tv_night.setText(night);
            holder.tv_temperature.setText(temperature);
        }else {
            holder.tv_data.setText("空空如也");
            holder.tv_day_time.setText("空空如也");
            holder.tv_night.setText("空空如也");
            holder.tv_temperature.setText("空空如也");
        }
    }

    @Override
    public int getItemCount() {
        return futureBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_data;      //日期
        private TextView tv_day_time;      //白天天气
        private TextView tv_night;     //晚上天气
        private TextView tv_temperature;       //温度
        //private TextView  tv_wind;       //风向

        public ViewHolder(View itemView) {
            super(itemView);
            tv_data = itemView.findViewById(R.id.item_weather_data);
            tv_day_time = itemView.findViewById(R.id.item_weather_day_time);
            tv_night = itemView.findViewById(R.id.item_weather_night);
            tv_temperature = itemView.findViewById(R.id.item_weather_temperature);

        }
    }
}
/**
 * "date": "2015-09-11",
 * "dayTime": "阴",
 * "night": "晴",
 * "temperature": "23°C/15°C",
 * "week": "星期五",
 * "wind": "北风3～4级无持续风向小于3级"
 */
