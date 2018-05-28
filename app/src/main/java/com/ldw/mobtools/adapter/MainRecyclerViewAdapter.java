package com.ldw.mobtools.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.ldw.mobtools.R;
import java.util.List;


public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private List<String> list;
    private Context context;

    public MainRecyclerViewAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    /**
     * 创建view和viewHolder
     */
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.item_main,parent,false);
        return new ViewHolder(itemview);
    }

    /**
     * 绑定数据到item
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final MainRecyclerViewAdapter.ViewHolder holder, final int position) {
        //根据位置绑定数据
        String name = list.get(position);
        holder.button.setText(name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private Button button;
        ViewHolder(View itemView) {
            super(itemView);
            //this.itemView = itemView;
            button = itemView.findViewById(R.id.item_main_button);

        }
    }


}
