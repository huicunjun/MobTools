package com.ldw.mobtools.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ldw.mobtools.R;

import com.ldw.mobtools.bean.WechatDataBean;

import java.util.List;

public class WechatRecyclerViewAdapter extends RecyclerView.Adapter<WechatRecyclerViewAdapter.ViewHolder> {

    private List<WechatDataBean.ResultBean.ListBean> datalist;
    private Context context;
   // private List<WechatClassifyBean.ResultBean> titlelist;


    public WechatRecyclerViewAdapter(List<WechatDataBean.ResultBean.ListBean> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_wechat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_title.setText(datalist.get(position).getTitle());
        holder.tv_subtitle.setText(datalist.get(position).getSubTitle());
        holder.tv_time.setText(datalist.get(position).getPubTime());
        holder.tv_id.setText(datalist.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title,tv_subtitle,tv_time,tv_id;
        private AppCompatImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.item_wechat_title);
            tv_subtitle = itemView.findViewById(R.id.item_wechat_subtitle);
            tv_time= itemView.findViewById(R.id.item_wechat_time);
            tv_id = itemView.findViewById(R.id.item_wechat_id);

        }
    }
}
/**
 *   "list": [
 *       {
 *         "cid": "1",
 *         "id": "2433405",
 *         "pubTime": "2016-02-2923:44",
 *         "sourceUrl": "http://mp.weixin.qq.com/s?__biz=MzA4MDQ1NTAyMQ==&mid=402016356&idx=2&sn=d43f4f07393814e344edffe608ac6619&scene=4#wechat_redirect",
 *         "subTitle": "你想要的百褶裙，这里一定有~",
 *         "title": "春日里摇曳裙摆，那些好看的百褶裙哪里买？"
 *       },
 */