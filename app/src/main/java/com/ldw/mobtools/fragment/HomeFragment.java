package com.ldw.mobtools.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ldw.mobtools.R;
import com.ldw.mobtools.adapter.MainRecyclerViewAdapter;
import com.ldw.mobtools.listener.RecyclerItemClickListener;
import com.ldw.mobtools.ui.CalendarActivity;
import com.ldw.mobtools.ui.HealthActivity;
import com.ldw.mobtools.ui.IDActivity;
import com.ldw.mobtools.ui.WeatherActivity;
import com.ldw.mobtools.ui.WechatActivity;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<String> names;
    private View view;
    private Intent intent;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_one, container, false);

        initdata();     //载入视图数据
        return view;

    }

    private void initdata() {
        //添加数据
        names = new ArrayList<>();
        names.add("万年历");
        names.add("天气查询");
        names.add("身份证查询");
        names.add("健康知识");
        names.add("IP查询");
        names.add("物流查询");
        names.add("微信精选");
        names.add("AA查询");
        names.add("BB查询");
        names.add("CC查询");

        //查找id
        RecyclerView recyclerView = view.findViewById(R.id.frag_one_rec);
        //设置点击事件
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        intent = new Intent(getActivity(),CalendarActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity(),WeatherActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                       intent = new Intent(getActivity(), IDActivity.class);
                        startActivity(intent);
                        break;
                    case 3:      //健康知识
                        intent = new Intent(getActivity(), HealthActivity.class);
                        startActivity(intent);
                        break;
                    case 6:      //微信精选
                        intent = new Intent(getActivity(), WechatActivity.class);
                        startActivity(intent);
                        break;
                }

            }

            @Override
            public void onLongClick(View view, int posotion) {
                Toast.makeText(getContext(), "hh"+posotion, Toast.LENGTH_SHORT).show();

            }
        }));
        //创建RecyclerView适配器
        MainRecyclerViewAdapter mainRecyclerViewAdapter = new MainRecyclerViewAdapter(names,getContext());
       //创建布局管理器
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //装上适配器
        recyclerView.setAdapter(mainRecyclerViewAdapter);



/**
 * 1.在Activity中可以直接调用getApplicationContext()，Activity.this/context获取相应的context。
 2.在Fragment中的使用方式则为：
 1.getActivity()；//获取包含该fragment的活动（activity）上下文
 2.getContext()；//获取该fragment上下文
 3.getActivity().getApplicationContext()；//通过包含该fragment的活动（activity）获取整个应用的上下文
 4.getContext().getApplicationContext()；//通过该fragment获取整个应用的上下文

 （在非特殊情况下（如：内存泄漏），这四种方式都可以正常使用，并且效果相同。）
 */
    }


}
