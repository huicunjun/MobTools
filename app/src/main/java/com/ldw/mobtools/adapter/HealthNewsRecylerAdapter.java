package com.ldw.mobtools.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldw.mobtools.R;
import com.ldw.mobtools.bean.HealthBean;
import com.ldw.mobtools.utils.LogUtils;

import java.util.List;

public class HealthNewsRecylerAdapter extends RecyclerView.Adapter<HealthNewsRecylerAdapter.ViewHolder> {

    private List<HealthBean.ResultBean.ListBean> listBeans;
    private Context context;

    public HealthNewsRecylerAdapter(List<HealthBean.ResultBean.ListBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public HealthNewsRecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_health, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogUtils.Logi("" + listBeans.size());

        if (listBeans.size() > 0) {
            holder.title.setText(listBeans.get(position).getTitle());
            holder.id.setText("ID : " + listBeans.get(position).getId());
            holder.content.setText(listBeans.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public void setData(List<HealthBean.ResultBean.ListBean> listBeans) {
        this.listBeans = listBeans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, id, content;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_health_title);
            id = itemView.findViewById(R.id.item_health_id);
            content = itemView.findViewById(R.id.item_health_content);

        }
    }
}
